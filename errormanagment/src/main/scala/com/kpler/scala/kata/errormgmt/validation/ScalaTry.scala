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

  def parseName(payload: Obj, key: String): Try[String] = ???

  def parseAge(payload: Obj): Try[Int] = ???

  def tryValidate(payload: Obj): Try[Subscriber] = ???

}
