package com.kpler.scala.kata.errormgmt.validation

import cats.data.Validated.{ Invalid, Valid }
import cats.data.{ NonEmptyList, ValidatedNel }
import cats.syntax.apply.*
import com.kpler.scala.kata.errormgmt.model.Subscriber
import ujson.Obj

import scala.util.{ Failure, Success, Try }
import com.kpler.scala.kata.errormgmt.validation.StrOrDoubleExt.toLongOrThrow

/*
Same as com.kpler.scala.kata.errormgmt.validation.ScalaEither
but the functions here return a Validated which:
- is a cats.data.Validated.Valid if the validation is successful
- a NonEmptyList with one cats.data.Validated.Invalid if it's not

Note the return type of the functions: ValidatedNel[SubscriberValidationError, Long]
ValidatedNel[+E, +A] is an alias for Validated[NonEmptyList[E], A]

You can build a NonEmptyList with one single element with: NonEmptyList.one

Unlike the Either, the Validated data type cumulates the errors.
Because of this property, Validated IS NOT a Monad, can you explain why ?

For comprehensions cannot be used with Validated, what exactly does it miss ?

You can combine Validated with 'mapN', one example of mapN with Option:

  case class Point(x: Int, y: Int)

  (Option(1), Option(2)).mapN(Point.apply) // Some(Point(1, 2))
  (Option(1), None).mapN(Point.apply) // None


 */

object CatsValidated {

  def parseId(payload: Obj): ValidatedNel[SubscriberValidationError, Long] = {

    payload.value.get("id") match {
      case None => Invalid(NonEmptyList.one(MissingKey("id")))
      case Some(id) =>
        id.strOpt.orElse(id.numOpt) match {
          case None => Failure(NumberFormatException(s"$id is not a numeric"))
          case Some(str: (String | Double)) => Try(str.toLongOrThrow)
        } match {
          case Success(idAsLong) if idAsLong < 0L => Invalid(NonEmptyList.one(NegativeValue("id")))
          case Success(idAsLong) => Valid(idAsLong)
          case Failure(_) => Invalid(NonEmptyList.one(InvalidType("id", classOf[Long])))
        }
    }
  }

  def parseName(payload: Obj, key: String): ValidatedNel[SubscriberValidationError, String] =
    payload.value.get(key) match {
      case None => Invalid(NonEmptyList.one(MissingKey(key)))
      case Some(name) =>
        name.strOpt match {
          case None => Invalid(NonEmptyList.one(InvalidType(key, classOf[String])))
          case Some(str) if str.length >= 3 && str.length <= 60 => Valid(str)
          case Some(_) => Invalid(NonEmptyList.one(LengthOutOfRange(key, 3, 60)))
        }
    }

  def parseAge(payload: Obj): ValidatedNel[SubscriberValidationError, Int] = payload.value.get("age") match {
    case None => Invalid(NonEmptyList.one(MissingKey("age")))
    case Some(age) =>
      age.numOpt match {
        case None => Invalid(NonEmptyList.one(InvalidType("age", classOf[Int])))
        case Some(db: Double) =>
          Try(db.toInt) match {
            case Success(ageAsInt) if ageAsInt >= 21 && ageAsInt <= 65 => Valid(ageAsInt)
            case Success(_) => Invalid(NonEmptyList.one(ValueOutOfRange("age", 21, 65)))
            case Failure(_) => Invalid(NonEmptyList.one(InvalidType("age", classOf[Int])))
          }
      }
  }

  def catsValidated(payload: Obj): ValidatedNel[SubscriberValidationError, Subscriber] = (
    parseId(payload),
    parseName(payload, "firstName"),
    parseName(payload, "lastName"),
    parseAge(payload)
  ).mapN(Subscriber.apply)
}
