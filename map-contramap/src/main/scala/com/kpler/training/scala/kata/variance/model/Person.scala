package com.kpler.training.scala.kata.variance.model

import cats.Show
import cats.syntax.all.*
import upickle.default.ReadWriter

case class Person(lastName: String, firstName: String)


object Person {

  given Show[Person] = Show[Map[String, String]].contramap(p => Map("lastName" -> p.lastName, "firstName" -> p.firstName))

  given Ordering[Person] = ???

  given ReadWriter[Person] = ???

}