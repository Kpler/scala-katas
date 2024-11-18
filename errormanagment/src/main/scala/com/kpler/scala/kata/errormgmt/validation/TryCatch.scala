package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import com.kpler.scala.kata.errormgmt.validation.StrOrDoubleExt.toLongOrThrow
import ujson.Obj

/*
 * Includes the functions that validate a JSON payload and attempt to convert into a Subscriber
 * To be valid the payload must contain :
 * - a non-negative long 'id' field
 * - a string 'firstName' field with a length between 3 and 60
 * - a string 'lastName' field with a length between 3 and 60
 * - an integer 'age' field whose value is between 21 and 65
 *
 * A valid payload would be:
 * {
 *  "id": 5621,
 *  "firstName": "Antoine",
 *  "lastName": "Dupont",
 *  "age": 27
 * }
 *
 *
 * The functions throw an SubscriberValidationException when they encounter an error
 * */
object TryCatch {

  def parseId(payload: Obj): Long = {
    payload.value.get("id") match {
      case None => throw SubscriberValidationException(MissingKey("id"))
      case Some(id) =>
        id.strOpt.orElse(id.numOpt) match {
          case None => throw SubscriberValidationException(InvalidType("id", classOf[Long]))
          case Some(str: (String | Double)) =>
            val idAsLong =
              try {
                str.toLongOrThrow
              } catch {
                case _: Throwable => throw SubscriberValidationException(InvalidType("id", classOf[Long]))
              }
            if (idAsLong < 0L) throw SubscriberValidationException(NegativeValue("id"))
            else idAsLong
        }
    }
  }

  def parseName(payload: Obj, key: String): String = {
    payload.value.get(key) match {
      case None => throw SubscriberValidationException(MissingKey(key))
      case Some(name) =>
        name.strOpt match {
          case None => throw SubscriberValidationException(InvalidType(key, classOf[String]))
          case Some(str) if str.length >= 3 && str.length <= 60 => str
          case Some(_) => throw SubscriberValidationException(LengthOutOfRange(key, 3, 60))
        }
    }
  }

  def parseAge(payload: Obj): Int = {
    payload.value.get("age") match {
      case None => throw SubscriberValidationException(MissingKey("age"))
      case Some(age) =>
        age.numOpt match {
          case None => throw SubscriberValidationException(InvalidType("age", classOf[Int]))
          case Some(ageAsDouble: Double) =>
            val idAsInt = ageAsDouble.toInt
            if (idAsInt >= 21 && idAsInt <= 65) idAsInt
            else throw SubscriberValidationException(ValueOutOfRange("age", 21, 65))
        }
    }
  }

  def tryCatchValidate(payload: Obj): Subscriber = Subscriber(
    parseId(payload),
    parseName(payload, "firstName"),
    parseName(payload, "lastName"),
    parseAge(payload),
  )
}
