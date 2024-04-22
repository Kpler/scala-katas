package com.kpler.scala.kata.io

import cats.effect.IO

object Parallel {

  /*
   * transform a list of IOs into an IO of List
   *
   * Every io should be run sequentially and the accumulated result stored into an IO
   * hint: use pattern matching and recursion
   */
  def sequence[A](ios: List[IO[A]]): IO[List[A]] = ???

  /*
   * same as sequence but run every io from the list in a separate fiber
   *
   * This one is hard
   *
   * hint: you should re-use sequence twice here
   */
  def parSequence[A](ios: List[IO[A]]): IO[List[A]] = ???

}
