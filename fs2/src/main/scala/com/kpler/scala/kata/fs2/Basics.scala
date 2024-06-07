package com.kpler.scala.kata.fs2

import cats.effect.{ IO, ResourceIO }
import fs2.io.file.{ Files, Path }
import fs2.{ Pure, Stream }

object Basics {

  /*
   Write the function that returns a stream that emits every even number by using Stream.iterate

    5 minutes
   */
  def evenNumbersStream: Stream[Pure, Int] = ???

  /*
   Write the function that returns a stream that emits every letter in the alphabet by using Stream.unfold

   7 minutes
   */
  def alphabetStream: Stream[Pure, Char] = ???

  /*
   Write the function that return a stream that emit every non empty line of a text file
   5 minutes
   Hints: Have a look there: fs2.io.file.Files
   */
  def streamedFileContent(file: Path): Stream[IO, String] = ???

  /*
  Write the function that return a Stream of KplerProduct read from a csv file
  The 2 fields of KplerProduct; id and name; correspond to the first 2 columns of the same names in the csv file
  The field separator is ';'
  Assume the fields are never null and correctly formatted
  An example of CSV file: src/test/resources/product.csv
  5 minutes
  Hints: use streamedFileContent
   */
  def readProductsFromCsv(file: Path): Stream[IO, KplerProduct] = ???

  /*
   Write the function that return a Stream of KplerProduct with their aliases
   The products are read from the csv file, as before
   The aliases are supplied by the productIdToAliases function that maps a product id to a list of aliases
   Note that productIdToAliases is effectful (ie return an IO)
   7 minutes
   Hints: reuse readProductsFromCsv, have a look at the functions in fs2.Stream that look like map but deal with effects
   */
  def mapProductToAliases(
    file: Path,
    productIdToAliases: Int => IO[List[String]],
  ): Stream[IO, (KplerProduct, List[String])] = ???

  def indexFilePath(inTheSameDir: Path)(filename: String): Option[Path] = inTheSameDir.parent.map(_ / filename)

  /*
  Write the function that concatenates the content of several files
  The paths of the file to concat are stored in the file fileOfPaths
  Assume the paths in the file are in the same directory as fileOfPaths
  7 minutes
  Hints: use streamedFileContent, flatMap or for comprehension, unNone and the supplied 'indexFilePath' function
   */
  def concatFiles(fileOfPaths: Path): Stream[IO, String] = ???

  trait DatabaseConnection {
    def insertLineIntoDatabase(line: String): IO[Int]
  }

  /*
   Write a function that inserts every line of a file to a remote database
   And return a FS2 stream with the inserted rows Id
   How can you make use of a ResourceIO with FS2 streams ?
   Likewise , how can you deal with an effectul function (insertLineIntoDatabase) and FS2 streams ?
   7 minutes
   */
  def insertRow(dbConnection: ResourceIO[DatabaseConnection], file: Path): Stream[IO, Int] = ???

}
