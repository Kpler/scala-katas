package com.kpler.scala.kata.errormgmt.validation

import cats.ApplicativeError
import cats.data.{ NonEmptyList, Validated, ValidatedNel }
import cats.implicits.*
import com.kpler.scala.kata.errormgmt.model.Subscriber
import com.kpler.scala.kata.errormgmt.validation.StrOrDoubleExt.toLongOrThrow
import ujson.Obj

import scala.util.{ Failure, Success, Try }

/*
 * The goal of the functions here is to generalize the validations by using the ApplicativeError type class
 * ApplicativeError takes 2 type parameters:
 * - one higher kinded type F which is the validation type the type class instance refers to (Try, Either, Validated)
 * - one error type E, which is the error Type
 *     - Throwable for a Try
 *     - the type stored in the Left for Either
 *     - the type stored in the Invalid for Validated
 *
 * Example:
 *
 *   type LeftStrOrA[A] = Either[String, A]
 *   ApplicativeError[LeftStrOrA, String]().pure(8) // Right(8)
 *   ApplicativeError[LeftStrOrA, String]().raiseError("It doesn't work") // Left("It doesn't work")
 *
 *   ApplicativeError generalizes the construction of a successful value with pure and the construction of the error with raiseError
 *
 *
 */
object CatsApplicativeError {

  def parseId[F[_], E](payload: Obj, mkError: SubscriberValidationError => E)(using
    F: ApplicativeError[F, E],
  ): F[Long] = {

    payload.value.get("id") match {
      case None => F.raiseError(mkError(MissingKey("id")))
      case Some(id) =>
        id.strOpt.orElse(id.numOpt) match {
          case None => F.raiseError(mkError(InvalidType("id", classOf[Long])))
          case Some(str: (String | Double)) =>
            Try(str.toLongOrThrow) match {
              case Success(idAsLong) if idAsLong < 0L => F.raiseError(mkError(NegativeValue("id")))
              case Success(idAsLong) => F.pure(idAsLong)
              case Failure(_) => F.raiseError(mkError(InvalidType("id", classOf[Long])))
            }
        }
    }
  }

  def parseName[F[_], E](payload: Obj, key: String, mkError: SubscriberValidationError => E)(using
    F: ApplicativeError[F, E],
  ): F[String] = ???

  def parseAge[F[_], E](payload: Obj, mkError: SubscriberValidationError => E)(using
    F: ApplicativeError[F, E],
  ): F[Int] = ???

  /*
   How are combined parseId, parseName and parseAge ?
   Hint: an instance of ApplicativeError exists for Validated, and Validated is not a Monad
   */
  def applicativeErrorValidate[F[_], E](payload: Obj, mkError: SubscriberValidationError => E)(using
    F: ApplicativeError[F, E]
  ): F[Subscriber] = ???

  /*
   rewrite com.kpler.scala.kata.errormgmt.validation.ScalaTry.tryValidate in terms of applicativeErrorValidate
   The ApplicativeError instance for Try is ApplicativeError[Try, Throwable]
   The mkError function is this case has to convert a SubscriberValidationError into a Throwable
   */
  def tryValidate_v2(payload: Obj): Try[Subscriber] = ???

  type LeftOrA[A] = Either[SubscriberValidationError, A]

  /*
   rewrite com.kpler.scala.kata.errormgmt.validation.ScalaEither.eitherValidate in terms of applicativeErrorValidate
   The ApplicativeError instance for Either is ApplicativeError[LeftOrA, SubscriberValidationError]
   The mkError function is this case has to leave the SubscriberValidationError unchanged
   */
  def eitherValidate_v2(payload: Obj): Either[SubscriberValidationError, Subscriber] = ???

  type InvalidOrA[A] = ValidatedNel[SubscriberValidationError, A]

  /*
   rewrite com.kpler.scala.kata.errormgmt.validation.CatsValidated.catsValidated in terms of applicativeErrorValidate
   What is the error type of the ApplicativeError instance for Validated ? ApplicativeError[InvalidOrA, ????]
   The mkError function has to convert a SubscriberValidationError to this error type
   */
  def catsValidated_v2(payload: Obj): ValidatedNel[SubscriberValidationError, Subscriber] = ???

}
