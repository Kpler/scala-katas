package com.kpler.scala.kata.io

import cats.Traverse
import cats.effect.IO
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import cats.effect.unsafe.implicits.global
import cats.Parallel as ParallelCE

import scala.concurrent.duration.DurationInt

class ParallelSpec extends AnyFlatSpec with GivenWhenThen with should.Matchers {

  "sequence" should "turn a list of IOs into an IO of list" in {

    Given("a list of IOs where each yield an integer value")
    val listOfIOs: List[IO[Int]] = (for i <- 0 to 8 yield IO.pure(i)).toList

    When("sequence is called with this list of IOs")
    val ioOfList = Parallel.sequence(listOfIOs)

    Then("The IO should be extracted out: List[IO[Int]] is turned into an IO[List[Int]]")
    ioOfList.unsafeRunSync() should be((for i <- 0 to 8 yield i).toList)
  }

  "parSequence" should "run each IO in parallel " in {

    Given("a list of IOs where each yield an integer value")
    val listOfIOs: List[IO[Int]] = List(
      IO.sleep(100.millis).as(0),
      IO.sleep(150.millis).as(1),
      IO.sleep(210.millis).as(2),
      IO.sleep(320.millis).as(3),
      IO.sleep(80.millis).as(4),
    )

    When("parSequence is called to run these IOs in parallel")
    val ioOfList = Parallel.parSequence(listOfIOs).timeout(350.millis)

    Then("The whole computation should take at most 320 millis plus a margin")
    ioOfList.unsafeRunSync() should be((for i <- 0 to 4 yield i).toList)
  }

  "sequence" should "do the same as the one from the Cats Effect library" in {

    Given("a list of IOs where each yield an integer value")
    val listOfIOs: List[IO[Int]] = (for i <- 0 to 8 yield IO.pure(i)).toList

    When("sequence is called with this list of IOs")
    val ioOfList = Parallel.sequence(listOfIOs)

    And("Cats Effect sequence is called with this list of IOs")
    val ioOfListCE = Traverse[List].sequence(listOfIOs)

    Then("The results should be the same")
    ioOfList.unsafeRunSync() should be(ioOfListCE.unsafeRunSync())
  }

  "parSequence" should "do the same as the one from the Cats Effect library" in {

    Given("a list of IOs where each yield an integer value")
    val listOfIOs: List[IO[Int]] = List(
      IO.sleep(100.millis).as(0),
      IO.sleep(150.millis).as(1),
      IO.sleep(210.millis).as(2),
      IO.sleep(320.millis).as(3),
      IO.sleep(80.millis).as(4),
    )

    When("sequence is called with this list of IOs")
    val ioOfList = Parallel.parSequence(listOfIOs).timeout(350.millis)

    And("Cats Effect parSequence is called with this list of IOs")
    val ioOfListCE = ParallelCE.parSequence(listOfIOs).timeout(350.millis)

    Then("The results should be the same")
    ioOfList.unsafeRunSync() should be(ioOfListCE.unsafeRunSync())
  }

}
