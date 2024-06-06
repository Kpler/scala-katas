package com.kpler.scala.kata.fs2

import cats.effect.IO
import fs2.io.file.Path
import fs2.{ Pure, Stream }

object Basics {

  /*
   Write the function that returns a stream that emits every even number by using Stream.iterate

    5 minutes
   */
  def evenNumbersStream: Stream[Pure, Int] = ???

  /*
   Write the function that returns a stream that emits every letter in the alphabet by using Stream.unfold

   7 minutes
   */
  def alphabetStream: Stream[Pure, Char] = ???

  /*
   Write the function that return a stream that emit every non empty line of a text file
   5 minutes
   * Hints: Have a look there: fs2.io.file.Files
   */
  def streamedFileContent(file: Path): Stream[IO, String] = ???
}
