package com.kpler.scala.kata.introduction

object Basics {

  private def sum(a: Int, b: Int): Int = a + b


  @main def myProgram(args: String*): Unit = {
    println(sum(2, 3))

    var a = 3
    println(a)
    a = 2
  }
}
