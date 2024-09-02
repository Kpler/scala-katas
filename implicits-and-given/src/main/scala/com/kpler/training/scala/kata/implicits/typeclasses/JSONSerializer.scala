package com.kpler.training.scala.kata.implicits.typeclasses

// part 1 - Type class definition
trait JSONSerializer[T] {
  def toJson(value: T): String
}