package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import ujson.Obj

import scala.util.{ Failure, Success, Try }
import com.kpler.scala.kata.errormgmt.validation.StrOrDoubleExt.toLongOrThrow

/*
Same as com.kpler.scala.kata.errormgmt.validation.TryCatch
but the functions here return an Either which:
- is a Right if the validation is successful
- a Left if it's not

 replace NumberFormatException with com.kpler.scala.kata.errormgmt.validation.InvalidType
 */
object ScalaEither {

  def parseId(payload: Obj): Either[SubscriberValidationError, Long] = {

    payload.value.get("id") match {
      case None => Left(MissingKey("id"))
      case Some(id) =>
        id.strOpt.orElse(id.numOpt) match {
          case None => Failure(NumberFormatException(s"$id is not a numeric"))
          case Some(str: (String | Double)) => Try(str.toLongOrThrow)
        } match {
          case Success(idAsLong) if idAsLong < 0L => Left(NegativeValue("id"))
          case Success(idAsLong) => Right(idAsLong)
          case Failure(_) => Left(InvalidType("id", classOf[Long]))
        }
    }
  }

  def parseName(payload: Obj, key: String): Either[SubscriberValidationError, String] = payload.value.get(key) match {
    case None => Left(MissingKey(key))
    case Some(name) =>
      name.strOpt match {
        case None => Left(InvalidType(key, classOf[String]))
        case Some(str) if str.length >= 3 && str.length <= 60 => Right(str)
        case Some(_) => Left(LengthOutOfRange(key, 3, 60))
      }
  }

  def parseAge(payload: Obj): Either[SubscriberValidationError, Int] = payload.value.get("age") match {
    case None => Left(MissingKey("age"))
    case Some(age) =>
      age.numOpt match {
        case None => Left(InvalidType("age", classOf[Int]))
        case Some(db: Double) =>
          Try(db.toInt) match {
            case Success(ageAsInt) if ageAsInt >= 21 && ageAsInt <= 65 => Right(ageAsInt)
            case Success(_) => Left(ValueOutOfRange("age", 21, 65))
            case Failure(_) => Left(InvalidType("age", classOf[Int]))
          }
      }
  }

  def eitherValidate(payload: Obj): Either[SubscriberValidationError, Subscriber] = for {
    id <- parseId(payload)
    firstName <- parseName(payload, "firstName")
    lastName <- parseName(payload, "lastName")
    age <- parseAge(payload)
  } yield Subscriber(id, firstName, lastName, age)
}
