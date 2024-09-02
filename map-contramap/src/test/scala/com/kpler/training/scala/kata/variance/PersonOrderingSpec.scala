package com.kpler.training.scala.kata.variance

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import com.kpler.training.scala.kata.variance.model.Person
import cats.syntax.all.*

class PersonOrderingSpec extends AnyFunSuite with should.Matchers {

  test("should show Person as a Map") {

    Person("Mbappe", "Kylian").show should be(
      Map("lastName" -> "Mbappe", "firstName" -> "Kylian").show
    )

  }

  /**
   * Exercise:
   * Implement the Ordering instance for Person in the com.kpler.training.scala.kata.variance.model.Person
   * companion object so that 'sorted' can be called on a List of Person
   * The persons should be sorted by lastName then firstname
   * Take inspiration from the Show[Person] defined instance just above.
   *
   * given Show[Person] = Show[Map[String, String]].contramap(p => Map("lastName" -> p.lastName, "firstName" -> p.firstName))
   *
   * Here is how you can decrypt the way 'contramap' works
   * You to Scala: 'I want you to show a Person as you would show a Map of String -> String
   * Scala to you: 'Ok, why not; but to achieve this, I need you to tell me how to transform a Person into a Map of String -> String, so that I can use the instance of Show[Map[String,String] on it'
   *
   * */
  test("should order a list of Person by lastName, firstName") {

    val unordered = List(
      Person("Singh Baath", "Preetwinder"),
      Person("Guillaume", "Sebastien"),
      Person("Guillaume", "Bernard"),
      Person("Brusnel", "Alban"),
      Person("Mironer", "Benjamin"),
      Person("Swertfager", "Austin"),
      Person("Janarthany", "Nandane"),
      Person("Ong", "Sze Swee")
    )

    unordered.sorted should be(
      List(
        Person("Brusnel", "Alban"),
        Person("Guillaume", "Bernard"),
        Person("Guillaume", "Sebastien"),
        Person("Janarthany", "Nandane"),
        Person("Mironer", "Benjamin"),
        Person("Ong", "Sze Swee"),
        Person("Singh Baath", "Preetwinder"),
        Person("Swertfager", "Austin")
      )
    )
  }


}
