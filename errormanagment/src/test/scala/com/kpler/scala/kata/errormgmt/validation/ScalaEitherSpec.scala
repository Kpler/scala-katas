package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import com.kpler.scala.kata.errormgmt.validation.ScalaEither.eitherValidate
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks.forEvery
import org.scalatest.prop.Tables.Table
import ujson.Obj

import scala.util.{ Left, Right }

class ScalaEitherSpec extends AnyFlatSpec with should.Matchers with GivenWhenThen {

  private val jsonKeys = List("id", "firstName", "lastName", "age")

  private val missingKeyTable = Table(
    "Key",
    jsonKeys *,
  )

  private val validPayload = Obj(
    "id" -> 5621L,
    "firstName" -> "Antoine",
    "lastName" -> "Dupont",
    "age" -> 27,
  )

  "eitherValidate" should "parse a valid payload into a Subscriber" in {

    Given("a valid JSON payload with all the required fields")
    val payload = validPayload
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)

    Then("the result should be a subscriber with the payload values")
    subscriber should be(Right(Subscriber(5621L, "Antoine", "Dupont", 27)))

  }

  "eitherValidate" should "parse a payload with the id supplied as an integer" in {

    Given("a valid JSON payload with all the required fields")
    val payload = Obj.from(validPayload.value.toMap.updated("id", 5621))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)

    Then("the result should be a subscriber with the payload values")
    subscriber should be(Right(Subscriber(5621L, "Antoine", "Dupont", 27)))

  }

  "eitherValidate" should "return a left if a key is missing" in {
    forEvery(missingKeyTable) { key =>
      Given(s"an invalid JSON payload with the field '$key' is missing")
      val payload = Obj.from(validPayload.value.toMap.removed(key))
      When("eitherValidate is called with the payload")
      val subscriber = eitherValidate(payload)
      Then("the left value should be a MissingKey")
      subscriber should be(Left(MissingKey(key)))
    }
  }

  "eitherValidate" should "return a left if id is not a Long" in {

    Given(s"an invalid JSON payload with the field 'id' not being a numeric")
    val payload = Obj.from(validPayload.value.toMap.updated("id", "not a numeric"))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a InvalidType")
    subscriber should be(Left(InvalidType("id", classOf[Long])))
  }

  "eitherValidate" should "return a left if id is negative" in {

    Given(s"an invalid JSON payload with a negative 'id' field")
    val payload = Obj.from(validPayload.value.toMap.updated("id", -4L))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a NegativeValue")
    subscriber should be(Left(NegativeValue("id")))
  }

  "eitherValidate" should "return a left if firstName is not a string" in {

    Given(s"an invalid JSON payload with the field 'firstName' not being a string")
    val payload = Obj.from(validPayload.value.toMap.updated("firstName", 1234))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a InvalidType")
    subscriber should be(Left(InvalidType("firstName", classOf[String])))
  }

  "eitherValidate" should "return a left if firstName is out of range" in {

    Given(s"an invalid JSON payload with a 2 long length field 'firstName'")
    val payload = Obj.from(validPayload.value.toMap.updated("firstName", "al"))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a LengthOutRange")
    subscriber should be(Left(LengthOutOfRange("firstName", 3, 60)))
  }

  "eitherValidate" should "return a left if lastName is not a string" in {

    Given(s"an invalid JSON payload with the field 'lastName' not being a string")
    val payload = Obj.from(validPayload.value.toMap.updated("lastName", 1234))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a InvalidType")
    subscriber should be(Left(InvalidType("lastName", classOf[String])))
  }

  "eitherValidate" should "return a left if lastName is out of range" in {

    Given(s"an invalid JSON payload with a 2 long length field 'lastName'")
    val payload = Obj.from(validPayload.value.toMap.updated("lastName", "al"))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a LengthOutRange")
    subscriber should be(Left(LengthOutOfRange("lastName", 3, 60)))
  }

  "eitherValidate" should "return a left if age is not a Int" in {

    Given(s"an invalid JSON payload with the field 'age' not being a numeric")
    val payload = Obj.from(validPayload.value.toMap.updated("age", "not a numeric"))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a InvalidType")
    subscriber should be(Left(InvalidType("age", classOf[Int])))
  }

  "eitherValidate" should "return a left if age is out of range" in {

    Given(s"an invalid JSON payload with an 'age' field value over 65")
    val payload = Obj.from(validPayload.value.toMap.updated("age", 70))
    When("eitherValidate is called with the payload")
    val subscriber = eitherValidate(payload)
    Then("the left value should be a ValueOutRange")
    subscriber should be(Left(ValueOutOfRange("age", 21, 65)))
  }

}
