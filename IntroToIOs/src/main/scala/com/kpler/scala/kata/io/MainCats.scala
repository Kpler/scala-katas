package com.kpler.scala.kata.io

import cats.effect.{ ExitCode, IO, IOApp }

import scala.util.Random

object MainCats extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val ioFive = IO.pure(5) // pure, must not contain any side effect
    val ioRandom = IO(Random.nextInt(100))
    val combinedIO = for {
      five <- ioFive
      random <- ioRandom
      _ <- IO.println(s"Random value is $random")
    } yield five + random
    combinedIO.flatMap(IO.println).as(ExitCode.Success)
  }

}
