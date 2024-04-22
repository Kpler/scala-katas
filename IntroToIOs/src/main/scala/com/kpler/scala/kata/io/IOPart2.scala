package com.kpler.scala.kata.io

import cats.effect.IO

object IOPart2 {

  // run the IO forever
  // hint: use flatMap or for comprehension and recursion
  def forever[A](io: IO[A]): IO[A] = ???

  /*
  re-run the IO at most 'nbRetries' if it fails

  It's a retry, so retry(io, 1) should run io at most twice (1 retry)
  retry(io, 2) should run io at most three times (2 retries) and so on

  hint: use IO.recoverWith, IO.raiseError and recursion
   */
  def retry[A](io: IO[A], nbRetries: Int): IO[A] = ???

  /*
   compute the sum of the sequence of integers starting from 1 to n

   sumIO(5).unsafeRunSync() => 1 + 2 + 3 + 4 + 5 = 15

   Do not use the formula (n * (n + 1)) / 2, the point here it to illustrate how IOs can tackle the
   stack overflow problem.
   Do not use accumulator either, as you would without IOs, to make the function tail-recursive
   Just use recursion

   hint: have a look to IO.defer
   */
  final def sumIO(n: Int): IO[Int] = ???
}
