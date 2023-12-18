package com.kpler.training.scala.kata.flatmap

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

import scala.concurrent.{Await, Future}

class FlatMapSpec extends AnyFunSuite with should.Matchers {

  /** Remember that for comprehensions for Lists in Scala look like Python's
   * Here: [ a + 1 for a in [1, 2, 3, 4] ]
   */
  test("Exercise 1: What is expected ?") {

    val result = for {
      a <- List(1, 2, 3, 4)
    } yield a + 1
    val expected = List(2, 3, 4, 5)
    result shouldBe expected
  }

  /** Remember that for comprehensions for Lists in Scala look like Python's
   * Here: [ a + b for a in [1, 2] for b in [3, 4] ]
   */
  test("Exercise 2: What is expected ?") {

    val result = for {
      a <- List(1, 2)
      b <- List(3, 4)
    } yield a + b
    val expected = List(4, 5, 5, 6)
    result shouldBe expected
  }

  /** The '::' operator prepends an element at the beginning of a list, example:
   * 1 :: List(2, 3, 4) => List(1, 2, 3, 4)
   */
  test("Exercise 3: What is expected ?") {

    val result = for {
      a <- List(1, 2)
      b <- a :: List(7, 8, 9)
    } yield b
    val expected = List(1, 7, 8, 9, 2, 7, 8, 9)
    result shouldBe expected
  }

  /** Hint: Remember that for comprehension in Scala is a syntactic sugar. Behind the scene it combines flatMap and map
   */
  test("Exercise 4: What is expected ?") {

    val result = List(1, 2).flatMap(a => List(3, 4).map(b => a + b))
    val expected = List(4, 5, 5, 6)
    result shouldBe expected
  }

  /** Returns a list with all elements from 'lst' duplicated
   *
   * Example:
   * duplicateElements(List(1,2,3)) => List(1,1,2,2,3,3)
   *
   * @param lst - the list of integer to duplicate the elements from
   * @return - the list with all elements from 'lst' duplicated
   */
  def duplicateElements(lst: List[Int]): List[Int] = for {
    a <- lst
    b <- List(a, a)
  } yield b

  test("Exercice 5: should duplicate the elements") {
    duplicateElements(List(1, 2, 3, 4)) shouldBe List(1, 1, 2, 2, 3, 3, 4, 4)
  }

  /** Given an ordered list l of integers, return a new list that contains the complete sequence from the first element of the list to the last one
   * The function should return l if it contains 0 or 1 element
   * Examples:
   *  - fillHoles(List(1, 4, 9)) => List(1, 2, 3, 4, 5, 6, 7, 8, 9)
   *  - fillHoles(List(4, 7)) => List(4, 5, 6, 7)
   *
   * Hints:
   * - use 'List#sliding' and 'toList'
   * - l(n) returns the (n-1)th element of the list l
   * - 0 until 4 returns List(0, 1, 2, 3)
   * - The ':+' operator appends one element at the end of a list: List(1, 2, 3) :+ 4 => List(1, 2, 3, 4)
   *
   * @param list - the list of integer to compute the missing sequence from
   * @return - a new list that contains the complete sequence from the first element of the list to the last one
   */
  def fillHoles(list: List[Int]): List[Int] = {
    if (list.isEmpty || list.size == 1) list
    else {
      val missesEnd = for {
        a <- list.sliding(2)
        b <- a.head until a(1)
      } yield b
      missesEnd.toList :+ list.last
    }
  }

  test("Exercice 5: should complete the missing sequence (hard)") {
    fillHoles(List(1, 4, 9)) shouldBe List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  def sumQuadraticFor(list: List[Int]): List[Int] = for {
    a <- list
    b <- list.tail
  } yield a + b

  /** Rewrite the previous function sumQuadraticFor with flatMap and map
   */
  def sumQuadraticMap(list: List[Int]): List[Int] = list.flatMap(a => list.tail.map(b => a + b))

  test("Exercice 6: should sumQuadraticFor compute the same output as sumQuadraticMap") {
    val lst = List(1, 5, 2, 3, 6)
    sumQuadraticFor(lst) shouldBe sumQuadraticMap(lst)
  }

  /** Options in Scala serve the same feature as Optional in Python: to deal with null/None values.
   * In Python, Optional is an union, for instance Optional[str] <=>  str | None
   * In Scala, Option is the parent class of 2 subtypes:
   * - Some : which indicates the value is set
   * - None: which indicates the value is not set (same as Python)
   * You can build Options by using any of 3 these 3 classes:
   * - Option(1) <=> Some(1)
   * - Option(null) <=> None
   * - Some(1)
   * - None
   * Another way to represent Options is a list with either 0 or 1 element
   * Like Lists, Option comes with map and flatMap and thus with for comprehension
   */
  test("Exercise 7: What is expected ?") {
    val result = for {
      a <- Some(1)
    } yield a + 1
    val expected = Some(2)
    result shouldBe expected
  }

  test("Exercise 8: What is expected ?") {
    val result = for {
      a <- Some(1)
      b <- Some(2)
    } yield a + b
    val expected = Some(3)
    result shouldBe expected
  }

  test("Exercise 9: What is expected ?") {
    val optB: Option[Int] = None
    val result = for {
      a <- Some(1)
      b <- optB
    } yield a + b
    val expected = None
    result shouldBe expected
  }

  test("Exercise 10: What is expected ?") {
    val optA: Option[Int] = None
    val result = for {
      a <- optA
      b <- Some(2)
    } yield a + b
    val expected = None
    result shouldBe expected
  }

  test("Exercise 11: What is expected (there is a trap) ?") {
    val optA: Option[Int] = None
    val result = for {
      _ <- optA // _ => do not use the value from optA
      b <- Some(2)
    } yield b
    val expected = None
    result shouldBe expected
  }

  import concurrent.ExecutionContext.Implicits.global
  import concurrent.duration.DurationInt

  /** Futures in Scala represent an asynchronous computation.
   * It runs a on different thread than the calling thread:
   *
   * val futA = Future("Some async computation")
   * val b = ...   // this line is reached no matter futA is completed or not
   *
   * Some callback function can be registered to handle the result of the future with 'onComplete'
   * Like with Lists and Option, Future can be chained with for comprehensions
   */
  test("With Futures") {
    val result = for {
      a <- Future(2)
      b <- Future(
        a * 2,
      ) // wait for the first future to complete before to start another one, using the result the of the first one
    } yield a + b
    val expected: Int = 6
    Await.result(result, 1.seconds) shouldBe expected
  }
}
