package com.kpler.scala.kata.fs2

import cats.effect.kernel.Resource
import cats.effect.{ IO, ResourceIO }
import fs2.io.file.{ Files, Path }
import fs2.{ Pure, Stream }

object Basics {

  /*
   Write the function that returns a stream that emits every even number by using Stream.iterate

    5 minutes
   */
  def evenNumbersStream: Stream[Pure, Int] = Stream.iterate(0)(_ + 2)

  /*
   Write the function that returns a stream that emits every letter in the alphabet by using Stream.unfold

   7 minutes
   */
  def alphabetStream: Stream[Pure, Char] = Stream.unfold('a')(character =>
    if (character.toInt == 'z'.toInt + 1) None else Some((character, (character + 1).toChar)),
  )

  // a more elegant way with iterate
  def alphabetStream2: Stream[Pure, Char] = Stream.iterate('a')(char => (char + 1).toChar).take(26)

  /*
   Write the function that return a stream that emit every non empty line of a text file
   5 minutes
   Hints: Have a look there: fs2.io.file.Files
   */
  def streamedFileContent(file: Path): Stream[IO, String] = Files[IO].readUtf8Lines(file).filter(_.nonEmpty)

  /*
  Write the function that return a Stream of KplerProduct read from a csv file
  The 2 fields of KplerProduct; id and name; correspond to the first 2 columns of the same names in the csv file
  The field separator is ';'
  Assume the fields are never null and correctly formatted
  An example of CSV file: src/test/resources/product.csv
  5 minutes
  Hints: use streamedFileContent
   */
  def readProductsFromCsv(file: Path): Stream[IO, KplerProduct] = streamedFileContent(file)
    .drop(1)
    .map(line => line.split(';').take(2))
    .map(fields => KplerProduct(fields(0).toInt, fields(1)))

  def productToProductWithAliases(
    productIdToAliases: Int => IO[List[String]],
  )(
    product: KplerProduct,
  ): IO[KplerProductWithAliases] =
    productIdToAliases(product.id).map(aliases => KplerProductWithAliases(product.id, product.name, aliases))

  /*
   Write the function that return a Stream of KplerProduct with their aliases
   The products are read from the csv file, as before
   The aliases are supplied by the productIdToAliases function that maps a product id to a list of aliases
   Note that productIdToAliases is effectful (ie return an IO)
   7 minutes
   Hints:
    - Implement productToProductWithAliases first and finds a means to combine it with readProductsFromCs.
    - Have a look at the functions in fs2.Stream that look like map but deal with effects
   */
  def mapProductToAliases(
    file: Path,
    productIdToAliases: Int => IO[List[String]],
  ): Stream[IO, KplerProductWithAliases] = readProductsFromCsv(file)
    .evalMap(productToProductWithAliases(productIdToAliases))

  def indexFilePath(inTheSameDir: Path)(filename: String): Option[Path] = inTheSameDir.parent.map(_ / filename)

  /*
  Write the function that concatenates the content of several files
  The paths of the file to concat are stored in the file fileOfPaths
  Assume the paths in the file are in the same directory as fileOfPaths
  7 minutes
  Hints: use streamedFileContent, flatMap or for comprehension, unNone and the supplied 'indexFilePath' function
   */
  def concatFiles(fileOfPaths: Path): Stream[IO, String] = streamedFileContent(fileOfPaths)
    .map(indexFilePath(fileOfPaths))
    .unNone
    .flatMap(streamedFileContent)

  def concatFilesForComp(fileOfPaths: Path): Stream[IO, String] = for {
    file <- streamedFileContent(fileOfPaths)
    filePath <- Stream.fromOption(indexFilePath(fileOfPaths)(file))
    line <- streamedFileContent(filePath)
  } yield line

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
  def insertRow(dbConnection: ResourceIO[DatabaseConnection], file: Path): Stream[IO, Int] = for {
    connection <- Stream.resource(dbConnection)
    line <- streamedFileContent(file)
    rowID <- Stream.eval(connection.insertLineIntoDatabase(line))
  } yield rowID

}
