package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import com.kpler.scala.kata.errormgmt.validation.TryCatch.tryCatchValidate
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks.forEvery
import org.scalatest.prop.Tables.Table
import ujson.Obj
import ujson.Value.{ JsonableInt, JsonableLong }

import scala.language.postfixOps

class TryCatchSpec extends AnyFlatSpec with should.Matchers with GivenWhenThen {

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

  "tryCatchValidate" should "parse a valid payload into a Subscriber" in {

    Given("a valid JSON payload with all the required fields")
    val payload = validPayload
    When("tryCatchValidate is called with the payload")
    val subscriber = tryCatchValidate(payload)

    Then("the result should be a subscriber with the payload values")
    subscriber should be(Subscriber(5621L, "Antoine", "Dupont", 27))

  }

  "tryCatchValidate" should "throw an exception if a key is missing" in {
    forEvery(missingKeyTable) { (key, expectedException) =>
      Given(s"an invalid JSON payload with the field '$key' is missing")
      val payload = Obj.from(validPayload.value.toMap.removed(key))
      When("tryCatchValidate is called with the payload")
      try {
        tryCatchValidate(payload)
        fail("should not reach this point")
      } catch {
        case e: Exception =>
          Then(
            "the caught exception should be a SubscriberValidationException with a message that wraps a MissingKey error",
          )
          e should be(expectedException)

      }
    }
  }

  "tryCatchValidate" should "throw an exception if id is not a Long" in {

    Given(s"an invalid JSON payload with the field 'id' not being a numeric")
    val payload = Obj.from(validPayload.value.toMap.updated("id", "not a numeric"))
    When("tryCatchValidate is called with the payload")
    try {
      tryCatchValidate(payload)
      fail("should not reach this point")
    } catch {
      case e: Exception =>
        Then(
          "the caught exception should be a SubscriberValidationException with a message that wraps a InvalidType error",
        )
        e should be(SubscriberValidationException(InvalidType("id", classOf[Long])))
    }
  }

  "tryCatchValidate" should "throw an exception if id is negative" in {

    Given(s"an invalid JSON payload with the field 'id' not being a numeric")
    val payload = Obj.from(validPayload.value.toMap.updated("id", -4L))
    When("tryCatchValidate is called with the payload")
    try {
      tryCatchValidate(payload)
      fail("should not reach this point")
    } catch {
      case e: Exception =>
        Then(
          "the caught exception should be a SubscriberValidationException with a message wraps a NegativeValue error",
        )
        e should be(SubscriberValidationException(NegativeValue("id")))
    }
  }

}
