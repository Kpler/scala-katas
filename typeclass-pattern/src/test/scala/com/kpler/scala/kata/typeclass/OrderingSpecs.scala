package com.kpler.scala.kata.typeclass

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class OrderingSpecs extends AnyFunSuite with should.Matchers {

  case class Person(firstName: String, lastName: String)

  given Ordering[Person] with {

    override def compare(x: Person, y: Person): Int = ???
  }

  test("should combine(1, 2) be 3") {
    val seb = Person("Sebastien", "Guillaume")
    val viacheslav = Person("Viacheslav", "Chudnovskyi")
    val misterPython = Person("Samir", "Naim")
    val kostas = Person("Konstantinos", "Maravegias")
    val despoina = Person("Despoina", "Papadatou")
    val andrey = Person("Andrey", "Volkov")

    List(
      seb, viacheslav, misterPython, kostas, despoina, andrey
    ).sorted shouldBe
      List(
        viacheslav,
        seb,
        kostas,
        misterPython,
        despoina,
        andrey,
      )

  }


}





