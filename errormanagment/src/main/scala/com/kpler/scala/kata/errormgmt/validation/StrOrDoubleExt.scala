package com.kpler.scala.kata.errormgmt.validation

object StrOrDoubleExt {

  extension (strOrDouble: String | Double)
    def toLongOrThrow: Long = strOrDouble match {
      case str: String => str.toLong
      case double: Double => double.toLong
    }
}
