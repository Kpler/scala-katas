package com.kpler.training.scala.kata.implicits.model

import com.kpler.training.scala.kata.implicits.typeclasses.JSONSerializer

case class Person(name: String, age: Int)

object Person {

  /*given JSONSerializer[Person] with {
    override def toJson(value: Person) = ???
  }*/


  given personJsonSerializer(using stringJsonSerializer: JSONSerializer[String], intJsonSerializer: JSONSerializer[Int]): JSONSerializer[Person] with {
    override def toJson(value: Person) = ???
  }
}
