package com.kpler.scala.kata.typeclass

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt

class SemigroupSpecs extends AnyFunSuite with should.Matchers {


  //1 definition of the trait
  trait Semigroup[A] {
    def combine(first: A, second: A): A
  }

  object Semigroup {

    //2  The definition of the implementations of the trait for concrete types
    given Semigroup[Int] with {
      override def combine(first: Int, second: Int): Int = ???
    }

    given Semigroup[String] with {
      override def combine(first: String, second: String): String = ???
    }

    given Semigroup[FiniteDuration] with {
      override def combine(first: FiniteDuration, second: FiniteDuration): FiniteDuration = ???
    }

    //3 api
    def combine[A](first: A, second: A)(using semigroup: Semigroup[A]): A = ???


    //4 ops / syntax
    object SemigroupSyntax {

      extension [A](a: A)(using semigroup: Semigroup[A])
        def combine(b: A): A = ???
    }
  }

  import Semigroup.given

  test("should combine(1, 2) be 3") {
    Semigroup.combine(1, 2) shouldBe 3
  }

  test("should combine('Scala', ' is great') be 3") {
    Semigroup.combine("Scala", " is great") shouldBe "Scala is great"
  }

  test("should combine('Scala', ' is great') be 3") {
    Semigroup.combine(3.minutes, 2.seconds) shouldBe 182.seconds
  }

  import Semigroup.SemigroupSyntax.*

  test("should combine(1, 2) be 3") {
    1.combine(2) shouldBe 3
  }
}


