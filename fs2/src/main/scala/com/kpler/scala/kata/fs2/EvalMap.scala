package com.kpler.scala.kata.fs2

import cats.effect.IO
import fs2.Stream

object EvalMap {
  /*
   Write a function that processes a stream of even numbers, applying a specified long-running computation to each number.
   The resulting stream should only contains computation results that are less than or equal to 100. And the stream should terminate afterwards.

   Hint: Utilize the existing evenNumbersStream and consider using `evalMap` and `takeWhile`. Remember that evenNumbersStream is an infinite stream.

   5 minutes
   */
  def computeSequential(longRunningComputation: Int => IO[Int]): Stream[IO, Int] = ???

  /*
   The previous function was running sequentially over the stream and might take quite long to collect the results.
   Write another version of the above method that runs the long-running computation in parallel (with a maximum concurrency of 2)

   Hint: look into `parEvalMap` on Stream

   3 minutes
   */
  def computeParallel(longRunningComputation: Int => IO[Int]): Stream[IO, Int] = ???
}
