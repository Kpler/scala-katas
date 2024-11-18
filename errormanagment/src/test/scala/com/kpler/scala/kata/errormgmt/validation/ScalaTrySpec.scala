package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import com.kpler.scala.kata.errormgmt.validation.ScalaTry.tryValidate
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks.forEvery
import org.scalatest.prop.Tables.Table
import ujson.Obj

import scala.language.postfixOps
import scala.util.{ Failure, Success }

class ScalaTrySpec extends AnyFlatSpec with should.Matchers with GivenWhenThen {

  private val jsonKeys = List("id", "firstName", "lastName", "age")

  private val missingKeyTable = Table(
    ("Key", "Expected exception"),
    jsonKeys.map(key => (key, SubscriberValidationException(MissingKey(key)))) *,
  )

  private val validPayload = Obj(
    "id" -> 5621L,
    "firstName" -> "Antoine",
    "lastName" -> "Dupont",
    "age" -> 27,
  )

  "tryValidate" should "parse a valid payload into a Subscriber" in {

    Given("a valid JSON payload with all the required fields")
    val payload = validPayload
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)

    Then("the result should be a subscriber with the payload values")
    subscriber should be(Success(Subscriber(5621L, "Antoine", "Dupont", 27)))

  }

  "tryValidate" should "parse a payload with the id supplied as an integer" in {

    Given("a valid JSON payload with all the required fields")
    val payload = Obj.from(validPayload.value.toMap.updated("id", 5621))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)

    Then("the result should be a subscriber with the payload values")
    subscriber should be(Success(Subscriber(5621L, "Antoine", "Dupont", 27)))

  }

  "tryValidate" should "return a failure if a key is missing" in {
    forEvery(missingKeyTable) { (key, expectedException) =>
      Given(s"an invalid JSON payload with the field '$key' is missing")
      val payload = Obj.from(validPayload.value.toMap.removed(key))
      When("tryValidate is called with the payload")
      val subscriber = tryValidate(payload)
      Then(
        "the caught exception should be a SubscriberValidationException with a message that wraps a MissingKey error",
      )
      subscriber should be(Failure(expectedException))
    }
  }

  "tryValidate" should "return a failure if id is not a Long" in {

    Given(s"an invalid JSON payload with the field 'id' not being a numeric")
    val payload = Obj.from(validPayload.value.toMap.updated("id", "not a numeric"))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    subscriber match {
      case Failure(SubscriberValidationException(InvalidType("id", _: Class[Long]))) =>
        Then("the failure should wrap a InvalidType error")
        succeed
      case Failure(other) =>
        fail(s"The wrapped error should be a InvalidType, current: ${other.getMessage}")
      case Success(_) =>
        fail("A Failure should be returned")
    }

  }

  "tryValidate" should "return a failure if id is negative" in {

    Given(s"an invalid JSON payload with a negative 'id' field")
    val payload = Obj.from(validPayload.value.toMap.updated("id", -4L))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    Then(
      "the caught exception should be a SubscriberValidationException that wraps a NegativeValue error",
    )
    subscriber should be(Failure(SubscriberValidationException(NegativeValue("id"))))
  }

  "tryValidate" should "return a failure if firstName is not a string" in {

    Given(s"an invalid JSON payload with the field 'firstName' not being a string")
    val payload = Obj.from(validPayload.value.toMap.updated("firstName", 1234))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    Then(
      "the caught exception should be a SubscriberValidationException that wraps a type mismatch error",
    )
    subscriber should be(
      Failure(SubscriberValidationException(InvalidType("firstName", classOf[String]))),
    )
  }

  "tryValidate" should "return a failure if firstName is out of range" in {

    Given(s"an invalid JSON payload with a 2 long length field 'firstName'")
    val payload = Obj.from(validPayload.value.toMap.updated("firstName", "al"))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    Then(
      "the caught exception should be a SubscriberValidationException that wraps a LengthOutOfRange error",
    )
    subscriber should be(Failure(SubscriberValidationException(LengthOutOfRange("firstName", 3, 60))))
  }

  "tryValidate" should "return a failure if lastName is not a string" in {

    Given(s"an invalid JSON payload with the field 'lastName' not being a string")
    val payload = Obj.from(validPayload.value.toMap.updated("lastName", 1234))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    Then(
      "the caught exception should be a SubscriberValidationException that wraps a type mismatch error",
    )
    subscriber should be(
      Failure(SubscriberValidationException(InvalidType("lastName", classOf[String]))),
    )
  }

  "tryValidate" should "return a failure if lastName is out of range" in {

    Given(s"an invalid JSON payload with a 2 long length field 'lastName'")
    val payload = Obj.from(validPayload.value.toMap.updated("lastName", "al"))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    Then(
      "the caught exception should be a SubscriberValidationException that wraps a LengthOutOfRange error",
    )
    subscriber should be(Failure(SubscriberValidationException(LengthOutOfRange("lastName", 3, 60))))
  }

  "tryValidate" should "return a failure if age is not a Int" in {

    Given(s"an invalid JSON payload with the field 'age' not being a numeric")
    val payload = Obj.from(validPayload.value.toMap.updated("age", "not a numeric"))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    subscriber match {
      case Failure(SubscriberValidationException(InvalidType("age", _: Class[Int]))) =>
        Then("the failure should wrap a InvalidType")
        succeed
      case Failure(other) =>
        fail(s"The wrapped error should be a InvalidType, current: ${other.getMessage}")
      case Success(_) =>
        fail("A Failure should be returned")
    }

  }

  "tryValidate" should "return a failure if age is out of range" in {

    Given(s"an invalid JSON payload with an 'age' field value over 65")
    val payload = Obj.from(validPayload.value.toMap.updated("age", 70))
    When("tryValidate is called with the payload")
    val subscriber = tryValidate(payload)
    Then(
      "the exception wrapped in a Failure should be a SubscriberValidationException that itself wraps a ValueOutOfRange error",
    )
    subscriber should be(Failure(SubscriberValidationException(ValueOutOfRange("age", 21, 65))))
  }

}
