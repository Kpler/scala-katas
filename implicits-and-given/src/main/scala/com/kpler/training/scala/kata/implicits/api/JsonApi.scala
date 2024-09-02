package com.kpler.training.scala.kata.implicits.api

import com.kpler.training.scala.kata.implicits.typeclasses.JSONSerializer

// part 3 - user-facing API

object JsonApi {

  def convert2Json[T](value: T)(using serializer: JSONSerializer[T]): String =  ???

}
