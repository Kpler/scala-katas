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

  def parseId(payload: Obj): ValidatedNel[SubscriberValidationError, Long] = ???

  def parseName(payload: Obj, key: String): ValidatedNel[SubscriberValidationError, String] = ???

  def parseAge(payload: Obj): ValidatedNel[SubscriberValidationError, Int] = ???

  def catsValidated(payload: Obj): ValidatedNel[SubscriberValidationError, Subscriber] = ???
}
