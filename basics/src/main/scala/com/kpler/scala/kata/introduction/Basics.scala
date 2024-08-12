package com.kpler.scala.kata.introduction

import scala.annotation.tailrec

object Basics {

  private def sum(a: Int, b: Int): Int = a + b


  def distribution(lst: List[Int]): Map[Int, Int] = {

    @tailrec
    def internal(currentList: List[Int], distrib: Map[Int, Int]): Map[Int, Int] = currentList match {
      case first :: rest => internal(rest, distrib.updated(first, distrib.getOrElse(first, 0) + 1))
      case Nil => distrib
    }

    internal(lst, Map.empty)
  }

  def insertSort(lst: List[Int]): List[Int] = {

    /*
     Insert value into the sorted list sorted and return the resulting list

     hints: use recursion and pattern matching on lst

     time: 8 minutes
     */
    def insert(value: Int, sorted: List[Int]): List[Int] = ???

    /*
     Insert every element from 'notSorted' into 'sorted' and return the resulting list

     hints:
     - insert the first value of notSorted into sorted then use recursion to insert the rest
     - use pattern matching again

     time: 7 minutes
    */
    def internal(sorted: List[Int], notSorted: List[Int]): List[Int] = ???

    lst
  }

  def concat[T](firstList: List[T], secondList: List[T]): List[T] = firstList ::: secondList

  /*
   return a list with every element of lst that satisfies the predicate

   time: 5 minutes
  */
  def filter[T](lst: List[T], predicate: T => Boolean): List[T] = ???

  /*
   apply the function mapper to every element of lst and return the resulting list

   time: 4 minutes
  */
  def map[A, B](lst: List[A], mapper: A => B): List[B] = ???

  /*
   like map but the mapper function return a List[B] and not a B
   The result is still a List[B]

   time: 7 minutes
  */
  def flatMap[A, B](lst: List[A], mapper: A => List[B]): List[B] = ???

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

    val toDistrib: List[Int] = List(5, 2, 3, 2, 3, 5, 5, 3, 3, 6)
    println(distribution(toDistrib))
    println(insertSort(toDistrib))

    val lstOfStrings: List[String] = List("one", "two", "three")
    val funcSum: (Int, Int) => Int = sum

    val listOfTuples = for {
      i <- 0 until 5
      j <- 0 until 6 if j % 2 == 0
    } yield (i, j)
    println(listOfTuples.toList)
  }
}
