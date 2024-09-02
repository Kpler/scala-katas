package com.kpler.training.scala.kata.implicits.syntax

import com.kpler.training.scala.kata.implicits.typeclasses.JSONSerializer

object JsonSyntax {

  // part 4 - extension methods just for the types we support
  extension[T] (value: T)
    def toJson(using serializer: JSONSerializer[T]): String = ???

  extension[T] (value: List[T])
    def toJson(using serializer: JSONSerializer[T]): String = ???

}
