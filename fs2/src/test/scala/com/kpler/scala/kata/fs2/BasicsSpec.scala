package com.kpler.scala.kata.fs2

import cats.effect.std.Queue
import cats.effect.unsafe.implicits.global
import cats.effect.{ IO, Ref, Resource, ResourceIO }
import com.kpler.scala.kata.fs2.Basics.*
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

    Then("the resulting compiled stream should contain all the non empty lines of the file")
    content should be(List("First line", "Second line", "Third line"))

  }

  "readProductsFromCsv" should "read a csv file and emit one KplerProduct for each parsed line" in {

    Given("a csv file that contains products")
    val filePath = Path(".") / "src" / "test" / "resources" / "products.csv"

    When("readProductsFromCsv is called with this file as argument")
    val products = readProductsFromCsv(filePath)

    Then("""
      the first 5 products of the compiled stream should be:
      - 1000 -> 76 Gasoline
      - 1002 -> 80 Gasoline
      - 1004 -> 83 Gasoline
      - 1006 -> 84 Gasoline
      - 1008 -> 85 Gasoline
    """.stripMargin)
    products.take(5).compile.toList.unsafeRunSync() should be(
      List(
        KplerProduct(1000, "76 Gasoline"),
        KplerProduct(1002, "80 Gasoline"),
        KplerProduct(1004, "83 Gasoline"),
        KplerProduct(1006, "84 Gasoline"),
        KplerProduct(1008, "85 Gasoline"),
      ),
    )

  }

  "mapProductToAliases" should "read a csv file and emit one KplerProduct with its aliases for each parsed line" in {

    Given("a csv file that contains products")
    val filePath = Path(".") / "src" / "test" / "resources" / "products.csv"

    And("an effectful function that map a product id to a list of aliases")
    val productToAliases: Int => IO[List[String]] =
      productId =>
        IO.pure(
          (for (i <- 0 until 2) yield s"alias $i of product $productId").toList,
        )

    When("mapProductToAliases is called")
    val productsWithAliases = mapProductToAliases(filePath, productToAliases)

    Then("""
      the first 2 products of the compiled stream should be:
      - (1000 -> 76 Gasoline) ["alias 0 of product 1000", "alias 1 of product 1000"]
      - (1000 -> 80 Gasoline) ["alias 0 of product 1002", "alias 1 of product 1002"]
    """.stripMargin)
    productsWithAliases.take(2).compile.toList.unsafeRunSync() should be(
      List(
        KplerProductWithAliases(1000, "76 Gasoline", List("alias 0 of product 1000", "alias 1 of product 1000")),
        KplerProductWithAliases(1002, "80 Gasoline", List("alias 0 of product 1002", "alias 1 of product 1002")),
      ),
    )
  }

  "concatFiles" should "concat the content of files whose paths are listed in an index file" in {

    Given("a index file that contains the path to other files: with-3-lines.txt and with-4-lines.txt")
    val filePath = Path(".") / "src" / "test" / "resources" / "list-of-files.txt"

    When("concatFiles is called")
    val content = concatFiles(filePath)

    Then("the resulting compiled stream should contain the content of with-3-lines.txt and with-4-lines.txt")
    content should be(
      List(
        "First line",
        "Second line",
        "Third line",
        "Fourth line",
        "Fifth line",
        "Sixth line",
        "Seventh line",
      ),
    )
  }

  def makeDbConnection(queue: Queue[IO, String], release: Ref[IO, Boolean]): ResourceIO[DatabaseConnection] =
    Resource.make(
      IO.pure(
        new DatabaseConnection {
          override def insertLineIntoDatabase(line: String): IO[Int] = queue.offer(line) >> queue.size
        },
      ),
    )(_ => release.set(true))

  "insertRow" should "insert the line of a file by using a DB connection" in {

    Given("a text file")
    val filePath = Path(".") / "src" / "test" / "resources" / "with-3-lines.txt"

    When("insertRow row is called with a mock for the database connection and this text file")
    val (rowIds, queueContent, released) =
      (for {
        queue <- Queue.bounded[IO, String](3)
        releasedRef <- Ref.of[IO, Boolean](false)
        rowIds <- insertRow(makeDbConnection(queue, releasedRef), filePath).compile.toList
        queueContent <- queue.tryTakeN(None)
        released <- releasedRef.get
      } yield (rowIds, queueContent, released)).unsafeRunSync()

    Then("the resulting compiled stream should contain the intermediate sizes of the queue")
    rowIds should be(List(1, 2, 3))

    And("the queue should contain the content of the file")
    queueContent should be(List("First line", "Second line", "Third line"))

    And("the db connection should be released")
    released should be(true)
  }
}
