package com.kpler.scala.kata.io

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.util.Random

object Main {

  def main(args: Array[String]): Unit = {
    val ioFive = IO.pure(5) // pure, must not contain any side effect
    val ioRandom = IO(Random.nextInt(100))
    val combinedIO = for {
      five <- ioFive
      random <- ioRandom
      _ <- IO.println(s"Random value is $random")
    } yield five + random
    combinedIO.flatMap(IO.println).unsafeRunSync()
  }
}
