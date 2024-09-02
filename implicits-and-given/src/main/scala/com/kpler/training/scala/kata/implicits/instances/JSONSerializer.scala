package com.kpler.training.scala.kata.implicits.instances

import com.kpler.training.scala.kata.implicits.typeclasses.JSONSerializer


// part 2 - type class instances
given stringSerializer: JSONSerializer[String] with {
  override def toJson(value: String) = ???
}

given intSerializer: JSONSerializer[Int] with {
  override def toJson(value: Int) = ???
}

given listSerializer[T] (using serializer: JSONSerializer[T]): JSONSerializer[List[T]] with {
  override def toJson(list: List[T]) = ???
}
