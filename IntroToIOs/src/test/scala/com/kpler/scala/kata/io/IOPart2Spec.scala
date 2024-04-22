package com.kpler.scala.kata.io

import cats.effect.{ IO, Ref }
import cats.effect.std.Queue
import cats.effect.unsafe.implicits.global
import com.kpler.scala.kata.io.IOPart2.{ forever, retry, sumIO }
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.concurrent.duration.DurationInt

class IOPart2Spec extends AnyFlatSpec with GivenWhenThen with should.Matchers {

  "forever" should "run the IO forever" in {

    Given("an IO that puts '5' into a Queue after 200 millisecond")
    val effect: Queue[IO, Int] => IO[Unit] = queue => IO.sleep(200.millis) >> queue.offer(5)

    When("forever is called with this IO and the resulting IO stops after 500 milliseconds")
    val foreverResult: Queue[IO, Int] => IO[Unit] = queue => forever(effect(queue)).timeout(500.millis)

    And("the queue is emptied after the forever IO has run")
    val emitted = for {
      queue <- Queue.bounded[IO, Int](10)
      _ <- foreverResult(queue).recoverWith(_ => IO.unit) // timeout makes the IO fails
      taken <- queue.tryTakeN(None)
    } yield taken

    Then("forever should have run the IO twice")
    emitted.unsafeRunSync() should be(List(5, 5))
  }

  "retry(2)" should "run the IO a third time after 2 unsuccessful attempts" in {

    Given("an IO that succeeds after 2 failures")
    val effect: Ref[IO, Int] => IO[Int] = ref =>
      ref
        .updateAndGet(_ + 1)
        .flatMap(runNumber => {
          if (runNumber == 3) IO.pure(5) // success
          else IO.raiseError(new RuntimeException(s"The run no $runNumber fails"))
        })

    When("this IO is retried 2 times")
    val retryResult: Ref[IO, Int] => IO[Int] = ref => retry(effect(ref), 2)

    Then("The final result is successful")
    val finalResult = for {
      ref <- Ref.of[IO, Int](0)
      retryResult <- retryResult(ref)
    } yield retryResult
    finalResult.unsafeRunSync() should be(5)
  }

  "retry(1)" should "run the IO a second time" in {

    Given("an IO that succeeds after 2 failures")
    val effect: Ref[IO, Int] => IO[Int] = ref =>
      ref
        .updateAndGet(_ + 1)
        .flatMap(runNumber => {
          if (runNumber == 3) IO.pure(5) // success
          else IO.raiseError(new RuntimeException(s"The run no $runNumber fails"))
        })

    When("this IO is retried only once")
    val retryResult: Ref[IO, Int] => IO[Int] = ref => retry(effect(ref), 1)

    Then("The final result is a failure")
    val finalResult = for {
      ref <- Ref.of[IO, Int](0)
      retryResult <- retryResult(ref)
    } yield retryResult
    finalResult.attempt.unsafeRunSync().left.map(_.getMessage) should be(Left("The run no 2 fails"))
  }

  "sumIO(20000)" should "compute 200010000" in {

    sumIO(20000).unsafeRunSync() should be(200010000)
  }

}
