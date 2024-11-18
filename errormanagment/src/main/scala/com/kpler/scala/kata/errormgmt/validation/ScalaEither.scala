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

 */
object ScalaEither {

  def parseId(payload: Obj): Either[SubscriberValidationError, Long] = ???

  def parseName(payload: Obj, key: String): Either[SubscriberValidationError, String] = ???

  def parseAge(payload: Obj): Either[SubscriberValidationError, Int] = ???

  def eitherValidate(payload: Obj): Either[SubscriberValidationError, Subscriber] = ???
}
