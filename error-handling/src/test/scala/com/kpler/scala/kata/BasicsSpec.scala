package com.kpler.scala.kata

import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import com.kpler.scala.kata.introduction.Basics.{insertSort, filter, map, flatMap}


class BasicsSpec extends AnyFlatSpec with GivenWhenThen {

  "insertSort" should "sort a list of integers" in {

    Given("a list of integers")
    val notSorted = List(4, 2, 6, 8, 1, 2, 5, 3, 3)

    When("insertSort is called with the list")
    val sorted = insertSort(notSorted)

    Then("the resulting list should contains all the elements in ascending order")
    assertResult(
      List(1, 2, 2, 3, 3, 4, 5, 6, 8)
    )(sorted)

  }

  "insertSort" should "support a list with one element" in {

    Given("a list with one single element")
    val notSorted = List(4)

    When("insertSort is called with the list")
    val sorted = insertSort(notSorted)

    Then("the resulting list should be equal to the original list")
    assertResult(notSorted)(sorted)

  }

  "insertSort" should "support an empty list" in {

    Given("a list with with no element")
    val notSorted = List.empty[Int]

    When("insertSort is called with the list")
    val sorted = insertSort(notSorted)

    Then("the resulting list should be equal to the original list")
    assertResult(notSorted)(sorted)

  }

  "filter" should "filter out element that don't satisfy a predicate" in {

    Given("a list of strings: ['I', 'love', 'Scala', 'and', 'functional', 'programming']")
    val listOfStrings = List("I", "love", "Scala", "and", "functional", "programming")


    When("filter is called with the list and a predicate that returns true if the length of a string is > 4")
    val filtered = filter(listOfStrings, s => s.length > 4)

    Then("the resulting list should only contain 'Scala', 'functional' and 'programming'")
    assertResult(List("Scala", "functional", "programming"))(filtered)
  }

  "map" should "apply a mapping function to every element of a list" in {

    Given("a list of integers: [1, 2, 3, 4, 5, 6]")
    val listOfIntegers = List(1, 2, 3, 4, 5, 6)


    When("map is called with the list and a function that multiply an integer by 2")
    val mapped = map(listOfIntegers, integer => integer * 2)

    Then("the resulting list should contain: [2, 4, 6, 8, 10, 12]")
    assertResult(
      List(2, 4, 6, 8, 10, 12)
    )(mapped)
  }


  "flatMap" should "apply a mapping function to every element of a list and flatten the result" in {

    Given("a list of integers: [1, 2, 3, 4, 5, 6]")
    val listOfIntegers = List(1, 2, 3, 4, 5, 6)


    When("flatMap is called with the list and a function that duplicates an integer")
    val mapped = flatMap(listOfIntegers, integer => List(integer, integer))

    Then("the resulting list should contain: [1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6]")
    assertResult(
      List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6)
    )(mapped)
  }

}
