package com.kpler.scala.kata.io

import cats.effect.IO
import cats.effect.std.Queue
import cats.effect.unsafe.implicits.global
import com.kpler.scala.kata.io.Fiber.parIOs
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.concurrent.duration.DurationInt

class FiberSpec extends AnyFlatSpec with GivenWhenThen with should.Matchers {

  "IOs" should "run sequentially since they are Monads" in {

    Given("one IO that yields '5' after 200 millisecond and records the event in a queue")
    val firstIO: Queue[IO, String] => IO[Int] = queue =>
      for {
        _ <- IO.sleep(200.millis)
        _ <- queue.offer("Generate 5")
      } yield 5

    And("and a second one that yields '6' with no delay and records the event in a queue as well")
    val secondIO: Queue[IO, String] => IO[Int] = queue => queue.offer("Generate 6") >> IO.pure(6)

    When("The 2 IOs are run")
    val chronEvents = for {
      queue <- Queue.bounded[IO, String](10)
      _ <- firstIO(queue)
      _ <- queue.offer("Between the 2 IOs")
      _ <- secondIO(queue)
      queueContent <- queue.tryTakeN(None)
    } yield queueContent

    Then("The chronological events should follow the IOs execution order")
    chronEvents.unsafeRunSync() should be(
      List(
        "Generate 5",
        "Between the 2 IOs",
        "Generate 6",
      ),
    )
  }

  "Fibers" should "run IOs concurrently" in {

    Given("one IO that yields '5' after 200 millisecond and records the event in a queue")
    val firstIO: Queue[IO, String] => IO[Int] = queue =>
      for {
        _ <- IO.sleep(200.millis)
        _ <- queue.offer("Generate 5")
      } yield 5

    And("and a second one that yields '6' with no delay and records the event in a queue as well")
    val secondIO: Queue[IO, String] => IO[Int] = queue => queue.offer("Generate 6") >> IO.pure(6)

    When("The 2 IOs are run on 2 different fibers")
    val chronEvents = for {
      queue <- Queue.bounded[IO, String](10)
      fiberOne <- firstIO(queue).start
      _ <- queue.offer("Between the 2 Fiber starts")
      fiberTwo <- secondIO(queue).start
      _ <- fiberOne.join
      _ <- fiberTwo.join
      queueContent <- queue.tryTakeN(None)
    } yield queueContent

    Then("The chronological events should NOT follow the IOs order")
    chronEvents.unsafeRunSync() should be(
      List(
        "Between the 2 Fiber starts",
        "Generate 6",
        "Generate 5",
      ),
    )
  }

  "parIOs" should "fail if the first io fails" in {

    Given("one IO that fails")
    val failedIO: IO[Int] = IO.raiseError(new RuntimeException("the first IO fails"))

    And("another one that succeeds")
    val successfulIO: IO[Int] = IO.pure(8)

    When("The 2 IOs are run in parallel")
    val resultIO: IO[(Int, Int)] = parIOs(failedIO, successfulIO)

    Then("The final resultIO is a failure")
    resultIO.attempt.unsafeRunSync().left.map(_.getMessage) should be(Left("the first IO fails"))
  }

  "parIOs" should "fail if the second io fails" in {

    Given("one IO that succeeds")
    val failedIO: IO[Int] = IO.pure(8)

    And("another one that fails")
    val successfulIO: IO[Int] = IO.raiseError(new RuntimeException("the second IO fails"))

    When("The 2 IOs are run in parallel")
    val resultIO: IO[(Int, Int)] = parIOs(failedIO, successfulIO)

    Then("The final resultIO is a failure")
    resultIO.attempt.unsafeRunSync().left.map(_.getMessage) should be(Left("the second IO fails"))
  }

  "parIOs" should "run the two IOs concurrently" in {

    Given("one IO that succeeds after 200 ms")
    val firstSuccessfulIO: IO[Int] = IO.sleep(200.millis) >> IO.pure(8)

    And("another one that succeeds as well after 300 ms")
    val secondSuccessfulIO: IO[Int] = IO.sleep(300.millis) >> IO.pure(9)

    When("The 2 IOs are run in parallel")
    val resultIO: IO[(Int, Int)] = parIOs(firstSuccessfulIO, secondSuccessfulIO)

    Then("The final resultIO is a success and takes at most 300ms with a margin")
    resultIO.timeout(350.millis).unsafeRunSync() should be((8, 9))
  }

}
