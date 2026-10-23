package com.kpler.scala.kata.introduction

import scala.annotation.tailrec

object Basics {

  private def sum(a: Int, b: Int): Int = a + b

  def distribution(lst: List[Int]): Map[Int, Int] = {

    // use pattern matching
    // recursion
    // use updated function on map
    @tailrec
    def internal(currentLst: List[Int], distrib: Map[Int, Int]): Map[Int, Int] = {

      currentLst match {
        case Nil => distrib
        case first :: rest =>
          internal(
            rest,
            distrib.updated(
              first,
              distrib.getOrElse(first, 0) + 1,
            ),
          )

      }
    }

    internal(lst, Map.empty)
  }

  def insertSort(lst: List[Int]): List[Int] = {

    /*
     Insert value into the sorted list sorted and return the resulting list

     hints: use recursion and pattern matching on lst

     */
    def insert(value: Int, sorted: List[Int]): List[Int] = ???

    /*
     Insert every element from 'notSorted' into 'sorted' and return the resulting list

     hints:
     - insert the first value of notSorted into sorted then use recursion to insert the rest
     - use pattern matching again

     */
    def internal(sorted: List[Int], notSorted: List[Int]): List[Int] = ???

    ???
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

  @main def sum(args: String*): Unit = {
    println(sum(4, 5))

    var a: Int = 0

    val lst = for i <- 0 until 10 yield i
    // lst(0) = 6
    val newList = lst.updated(0, 6)
    println(newList)
    println(newList.head)
    println(newList.tail)

    val first = newList.head
    val rest = newList.tail
    newList.toList match {
      case oneValue :: Nil => println("single value")

      case first :: rest => {
        println(first)
        println(rest)
      }

      case Nil => println("empty list")
    }

    print(distribution(newList.toList))

  }

}
