package com.kpler.scala.kata.io

import cats.effect.IO
import scala.concurrent.duration.DurationInt

object IOPart2 {

  // run the IO forever
  // hint: use flatMap or for comprehension and recursion
  def forever[A](io: IO[A]): IO[A] = io.flatMap(_ => forever(io))

//  def forever[A](io: IO[A]): IO[A] = for {
//    _ <- io
//    res <- forever(io)
//  } yield res

  /*
  re-run the IO at most 'nbRetries' if it fails

  It's a retry, so retry(io, 1) should run io at most twice (1 retry)
  retry(io, 2) should run io at most three times (2 retries) and so on

  hint: use IO.recoverWith, IO.raiseError and recursion
   */
  def retry[A](io: IO[A], nbRetries: Int): IO[A] = io.recoverWith {
    case _ if nbRetries > 0 => retry(io, nbRetries - 1)
    case err if nbRetries == 0 => IO.raiseError(err)
  }
  /*
   compute the sum of the sequence of integers starting from 1 to n

   sumIO(5).unsafeRunSync() => 1 + 2 + 3 + 4 + 5 = 15

   Do not use the formula (n * (n + 1)) / 2, the point here it to illustrate how IOs can tackle the
   stack overflow problem.
   Do not use accumulator either, as you would without IOs, to make the function tail-recursive
   Just use recursion

   hint: have a look to IO.defer
   */
  final def sumIO2(n: Int): IO[Int] = IO.defer(
    if (n <= 0) IO.pure(0)
    else {
      for {
        res <- sumIO(n - 1)
      } yield n + res
    },
  )

  final def sumIO(n: Int): IO[Int] =
    IO.defer(
      n match
        case 0 => IO.pure(0)
        case _ => sumIO(n - 1).map(_ + n),
    )

  final def sum(n: Int): Int = if (n <= 0) 0 else n + sum(n - 1)
}
