package com.kpler.training.scala.kata.variance

import cats.effect.{IO, Resource}
import cats.implicits.catsKernelStdMonoidForString
import cats.syntax.all.*

import java.io.{FileInputStream, FileOutputStream}
import scala.io.Source

object FileUtils {
  def fileContent(filename: String): IO[String] = Resource
    .make(IO.blocking(Source.fromFile(filename)))(source => IO.blocking(source.close()))
    .use(buffer => IO.blocking(buffer.getLines().toList).map(_.combineAll))

  def fileBinaryContent(filename: String): IO[Array[Byte]] = Resource.fromAutoCloseable(IO.blocking(new FileInputStream(filename)))
    .use(outputStream => IO.blocking(outputStream.readAllBytes))

  def writeBinaryFile(filename: String, content: Array[Byte]) = Resource.fromAutoCloseable(IO.blocking(new FileOutputStream(filename)))
    .use(outputStream => IO.blocking(outputStream.write(content)))

}
