package com.kpler.scala.kata.introduction

object Basics {

  private def sum(a: Int, b: Int): Int = a + b


  @main def myProgram(args: String*): Unit = {
    println(sum(2, 3))

    var a = 3
    println(a)
    a = 2

    val lst = List(1, 2, 3, 4)
    println(lst)
    println(lst(1))
    val newList = lst.updated(1, 5)
    println(newList)

    println(newList.head)
    println(newList.tail)
    newList match
      case first :: rest =>
        println(first)
        println(rest)
      case Nil => println("list is empty")

  }
}
