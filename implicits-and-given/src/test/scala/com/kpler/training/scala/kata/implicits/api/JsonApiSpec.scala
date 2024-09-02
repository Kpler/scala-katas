package com.kpler.training.scala.kata.implicits.api

import com.kpler.training.scala.kata.implicits.api.JsonApi.convert2Json
import com.kpler.training.scala.kata.implicits.instances.given
import com.kpler.training.scala.kata.implicits.model.Person
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class JsonApiSpec extends AnyFunSuite with should.Matchers {


  /**
   * Implements:
   * - stringSerializer in com.kpler.training.scala.kata.implicits.instances.JSONSerializer.scala
   * - convert2Json in com.kpler.training.scala.kata.implicits.api.JsonApi
   */
  test("should convert an string into a JSON string") {
    convert2Json("I love Scala Katas") shouldBe "\"I love Scala Katas\""
  }


  /**
   * Implements:
   * - intSerializer in com.kpler.training.scala.kata.implicits.instances.JSONSerializer.scala
   */
  test("should convert an integer into a JSON string") {
    convert2Json(3) shouldBe "3"
  }


  /**
   * Implements:
   * - JSONSerializer in com.kpler.training.scala.kata.implicits.model.Person
   * - personJsonSerializer  in com.kpler.training.scala.kata.implicits.model.Person (comment the previous one out)
   */
  test("should convert a person into a JSON string") {

    import com.kpler.training.scala.kata.implicits.model.Person.given

    convert2Json(Person("Kylian Mbappe", 24)) shouldBe
      """
        |{
        |"name": "Kylian Mbappe",
        |"age": 24
        |}
        |""".stripMargin
  }


  /**
   * Implements:
   * - listSerializer in com.kpler.training.scala.kata.implicits.instances.JSONSerializer.scala
   * Hint: use mkString list method
   */
  test("should convert a list of strings into a JSON array") {

    convert2Json(List("One", "Two", "Three")) shouldBe "[\"One\",\"Two\",\"Three\"]"
  }


  test("should convert a list of persons into a JSON array") {

    import com.kpler.training.scala.kata.implicits.model.Person.given

    convert2Json(
      List(
        Person("Kylian Mbappe", 24),
        Person("Antoine Dupont", 26)
      )
    ).trim shouldBe
      """
        |[
        |{
        |"name": "Kylian Mbappe",
        |"age": 24
        |}
        |,
        |{
        |"name": "Antoine Dupont",
        |"age": 26
        |}
        |]
        |""".stripMargin.trim
  }
}
