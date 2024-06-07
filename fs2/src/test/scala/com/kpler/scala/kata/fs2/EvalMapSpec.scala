package com.kpler.scala.kata.fs2

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.kpler.scala.kata.fs2.EvalMap.{ computeParallel, computeSequential }
import fs2.Stream
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import scala.concurrent.duration.*

class EvalMapSpec extends AnyFlatSpec with GivenWhenThen with should.Matchers {
  def computation(i: Int) = IO.sleep(100.millis).as(i * 10)

  "computeSequential" should "should compute 6 results in time" in {
    val computes = computeSequential(computation).take(6).compile.toList.timeout(690.millis).unsafeRunSync()

    computes should be(List(0, 20, 40, 60, 80, 100))
  }

  "computeParallel" should "should compute 6 results in time" in {
    val computes = computeParallel(computation).take(6).compile.toList.timeout(390.millis).unsafeRunSync()

    computes should be(List(0, 20, 40, 60, 80, 100))
  }
}
