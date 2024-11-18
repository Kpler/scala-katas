package com.kpler.scala.kata.errormgmt.validation

import com.kpler.scala.kata.errormgmt.model.Subscriber
import cats.data.Validated.{ Invalid, Valid }
import com.kpler.scala.kata.errormgmt.validation.CatsValidated.catsValidated
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import ujson.{ Num, Obj, Str }

class CatsValidatedSpec extends AnyFlatSpec with should.Matchers with GivenWhenThen {

  private val validPayload = Obj(
    "id" -> 5621L,
    "firstName" -> "Antoine",
    "lastName" -> "Dupont",
    "age" -> 27,
  )

  "catsValidated" should "parse a valid payload into a Subscriber" in {

    Given("a valid JSON payload with all the required fields")
    val payload = validPayload
    When("eitherValidate is called with the payload")
    val subscriber = catsValidated(payload)

    Then("the result should be a subscriber with the payload values")
    subscriber should be(Valid(Subscriber(5621L, "Antoine", "Dupont", 27)))

  }

  "catsValidated" should "cumulate errors" in {

    Given("an invalid JSON payload with multiple errors")
    val payload = Obj.from(
      validPayload.value.toMap
        .removed("id")
        .updated("firstName", Num(1234))
        .updated("lastName", Str("al"))
        .updated("age", "not a numeric"),
    )
    When("catsValidated is called with the payload")
    val subscriber = catsValidated(payload)

    Then("the result should include all the errors on the different fields")
    subscriber.leftMap(_.toList) should be(
      Invalid(
        List(
          MissingKey("id"),
          InvalidType("firstName", classOf[String]),
          LengthOutOfRange("lastName", 3, 60),
          InvalidType("age", classOf[Int]),
        ),
      ),
    )

  }

}
