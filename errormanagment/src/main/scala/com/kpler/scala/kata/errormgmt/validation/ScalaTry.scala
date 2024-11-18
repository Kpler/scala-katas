package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import com.kpler.scala.kata.errormgmt.validation.StrOrDoubleExt.toLongOrThrow
import ujson.Obj

import scala.util.{ Failure, Success, Try }

/*
Same as com.kpler.scala.kata.errormgmt.validation.TryCatch
but the functions here return a Try which:
- is a Success if the validation is successful
- a failure if it's not
The function don't throw any exception anymore
 */
object ScalaTry {

  def parseId(payload: Obj): Try[Long] = {

    payload.value.get("id") match {
      case None => Failure(SubscriberValidationException(MissingKey("id")))
      case Some(id) =>
        id.strOpt.orElse(id.numOpt) match {
          case None => Failure(SubscriberValidationException(InvalidType("id", classOf[Long])))
          case Some(str: (String | Double)) =>
            Try(str.toLongOrThrow) match {
              case Success(idAsLong) if idAsLong < 0L => Failure(SubscriberValidationException(NegativeValue("id")))
              case Failure(_) => Failure(SubscriberValidationException(InvalidType("id", classOf[Long])))
              case Success(idAsLong) => Success(idAsLong)
            }
        }
    }
  }

  def parseName(payload: Obj, key: String): Try[String] = {
    payload.value.get(key) match {
      case None => Failure(SubscriberValidationException(MissingKey(key)))
      case Some(name) =>
        name.strOpt match {
          case None => Failure(SubscriberValidationException(InvalidType(key, classOf[String])))
          case Some(str) if str.length >= 3 && str.length <= 60 => Success(str)
          case Some(_) => Failure(SubscriberValidationException(LengthOutOfRange(key, 3, 60)))
        }
    }
  }

  def parseAge(payload: Obj): Try[Int] = {
    payload.value.get("age") match {
      case None => Failure(SubscriberValidationException(MissingKey("age")))
      case Some(age) =>
        age.numOpt match {
          case None => Failure(SubscriberValidationException(InvalidType("age", classOf[Int])))
          case Some(db: Double) =>
            val ageAsInt = db.toInt
            if (ageAsInt >= 21 && ageAsInt <= 65) Success(ageAsInt)
            else Failure(SubscriberValidationException(ValueOutOfRange("age", 21, 65)))
        }
    }
  }

  def tryValidate(payload: Obj): Try[Subscriber] = for {
    id <- parseId(payload)
    firstName <- parseName(payload, "firstName")
    lastName <- parseName(payload, "lastName")
    age <- parseAge(payload)
  } yield Subscriber(id, firstName, lastName, age)

}
