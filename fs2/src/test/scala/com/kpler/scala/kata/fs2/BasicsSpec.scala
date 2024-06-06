package com.kpler.scala.kata.fs2

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.kpler.scala.kata.fs2.Basics.{ alphabetStream, evenNumbersStream, streamedFileContent }
import fs2.Stream
import fs2.io.file.Path
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class BasicsSpec extends AnyFlatSpec with GivenWhenThen with should.Matchers {

  "evenNumbersStream" should "should emit every even number" in {

    evenNumbersStream.take(6).toList should be(List(0, 2, 4, 6, 8, 10))

  }

  "alphabetStream" should "emit every letter in the alphabet" in {
    val alphabet = (for a <- 'a' to 'z' yield a).toList
    alphabetStream.toList should be(alphabet)

  }

  "streamedFileContent" should "emit every non empty line of a file" in {

    Given("a text file")
    val filePath = Path(".") / "src" / "test" / "resources" / "with-3-lines.txt"

    When("streamedFileContent is called with this file as argument")
    val content = streamedFileContent(filePath).compile.toList.unsafeRunSync()

    Then("the result should contain all the non empty line lines of the file")
    content should be(List("First line", "Second line", "Third line"))

  }

}
