package com.kpler.training.scala.kata.implicits.syntax

import com.kpler.training.scala.kata.implicits.instances.given
import com.kpler.training.scala.kata.implicits.model.Person
import com.kpler.training.scala.kata.implicits.syntax.JsonSyntax.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class JsonSyntaxSpec extends AnyFunSuite with should.Matchers {


  /**
   * Implements the first com.kpler.training.scala.kata.implicits.syntax.JsonSyntax extension
   */
  test("should convert an string into a JSON string by calling the extension method") {
    "I love Scala Katas".toJson shouldBe "\"I love Scala Katas\""
  }


  /**
   * Implements the second com.kpler.training.scala.kata.implicits.syntax.JsonSyntax extension
   */
  test("should convert a list of persons into a JSON array by calling the extension method") {

    import com.kpler.training.scala.kata.implicits.model.Person.given

    List(
      Person("Kylian Mbappe", 24),
      Person("Antoine Dupont", 26)
    ).toJson.trim shouldBe
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
