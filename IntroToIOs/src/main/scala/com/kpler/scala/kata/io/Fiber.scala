package com.kpler.scala.kata.io

import cats.effect.IO

object Fiber {

  /*
   run ioa and iob in two separate fibers and return a tuple with the result of the 2 if they are successful.
   If either fiber is failed, return a failed IO with the error
   If either fiber is cancelled, return a failed IO with a Runtime Exception: "One fiber was cancelled"
   When 'join' is called on a fiber, it returns a IO[Outcome[IO, Throwable, A]]
   Outcome[IO, Throwable, A] can either be:
   - Succeeded(a: IO[A])
   - Errored(e: Throwable)
   - Cancelled()
   hint: have a look to 'embed' in Outcome
   */
  def parIOs[A, B](ioa: IO[A], iob: IO[B]): IO[(A, B)] = ???
}
