package com.kpler.training.scala.kata.variance

import cats.effect.IO
import com.kpler.training.scala.kata.variance.model.Person
import munit.CatsEffectSuite
import org.scalatest.matchers.should
import upickle.default.{read, reader, write, writer}


/**
 * A standard use case of the usage of map/contramap is a type constructor that deserializes/serializes some type
 * - map is used upon deserialization (read)
 * - contramap is used upon serialization (write)
 * The libray that provides the deserializer/serializer type constructor usually support built-in standard types.
 *
 * map/deserialization: The type constructor knows how to deserialize the type A from some input stream (file, topic, whatever ...)
 * By supplying a function that converts A to B, we can get a new deserializer of B
 *
 * trait Deserializer[A] {
 *
 * def map[B](f:A => B): Deserializer[B]
 * }
 *
 * contramap/serialization: The type constructor knows how to serialize the type A to some input stream (file, topic, whatever ...)
 * By supplying a function that converts B to A, we can get a new serializer of B
 *
 * trait Serializer[A] {
 *
 * def contramap[B](f:B => A): Serializer[B]
 * }
 */
class SerdeSpec extends CatsEffectSuite with should.Matchers {

  val kylian = Person("Mbappe", "Kylian")


  test("should deserialize a Person by mapping a Map to a Person") {

    FileUtils.fileContent("src/test/resources/mbappe.json")
      .flatMap(content =>
        IO(
          read[Person](content)(using reader[Map[String, String]].map(dict => Person(dict("lastName"), dict("firstName"))))
        )

      ).map(person => person should be(kylian))
  }


  test("should serialize a Person by contramapping a Person to a Map") {
    IO(
      write[Person](kylian)(using writer[Map[String, String]].comap(person => Map("lastName" -> person.lastName, "firstName" -> person.firstName)))
    ).map(str => str should be("{\"lastName\":\"Mbappe\",\"firstName\":\"Kylian\"}"))

  }


  import com.kpler.training.scala.kata.variance.model.Person.given

  /**
   * Upickle also provide a type constructor 'ReadWriter' that handles both serialization and deserialization
   * Both map and contramap functions are supplied to bimap to define the 2 operations
   *
   * Exercise:
   * Implement the ReadWriter instance for Person in the com.kpler.training.scala.kata.variance.model.Person
   * companion object so that the two following tests, which are very similar to the previous ones, pass
   *
   * Do not use the Upickle macroRW macro but Map[String, String] as before
   */
  test("should deserialize a Person by using the given ReadWriter[Person] instance") {

    FileUtils.fileContent("src/test/resources/mbappe.json")
      .flatMap(content => IO(read[Person](content))
      ).map(person => person should be(kylian))
  }

  test("should serialize a Person by using the given ReadWriter[Person] instance") {
    IO(
      write[Person](kylian)
    ).map(str => str should be("{\"lastName\":\"Mbappe\",\"firstName\":\"Kylian\"}"))

  }

}
