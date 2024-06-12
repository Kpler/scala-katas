package com.kpler.scala.kata.io

import cats.effect.IO
import munit.CatsEffectSuite
import org.scalatest.matchers.should

class testIOSpec extends CatsEffectSuite with should.Matchers {

  // 1 - sequence two IOs and take the result of the LAST one
  // hint: use flatMap or for comprehension
  def sequenceTakeLast[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    for {
      a <- ioa
      b <- iob
    } yield b

  test("should example") {
    5 should be(5)
  }

  test("sequenceTakeLast should run the 2 IOs and return the result from the second one") {
    var firstEffectRun: Boolean = false

    def firstIO(value: Int): IO[Int] = for {
      _ <- IO {
        firstEffectRun = true
      }
      res <- IO(value)
    } yield res

    val combined: IO[Int] = for {
      result <- sequenceTakeLast(
        firstIO(4),
        IO(5),
      )
    } yield result
    firstEffectRun shouldBe false
    IO.apply(combined should be(5))
    combined.unsafeRunSync() should be(5)
    firstEffectRun shouldBe true

  }
}
