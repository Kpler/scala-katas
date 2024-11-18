package com.kpler.scala.kata.errormgmt.validation

import cats.Show

trait SubscriberValidationError

case class MissingKey(key: String) extends SubscriberValidationError

case class LengthOutOfRange(key: String, lower: Int, upper: Int) extends SubscriberValidationError

case class ValueOutOfRange(key: String, lower: Int, upper: Int) extends SubscriberValidationError

case class NegativeValue(key: String) extends SubscriberValidationError

case class InvalidType[A](key: String, expected: Class[A]) extends SubscriberValidationError

object SubscriberValidationError {

  given Show[SubscriberValidationError] with {
    override def show(t: SubscriberValidationError): String = t match {
      case MissingKey(key: String) => s"key '$key' is missing"
      case LengthOutOfRange(key: String, lower: Int, upper: Int) =>
        s"length of '$key' value must be between $lower and $upper"
      case ValueOutOfRange(key: String, lower: Int, upper: Int) =>
        s"numeric value of '$key' must be between $lower and $upper"
      case NegativeValue(key: String) => s"value of key '$key' is negative"
      case InvalidType(key: String, expected: Class[_]) =>
        s"key '$key' has invalid type, expected type is ${expected.getName}"
    }
  }
}
