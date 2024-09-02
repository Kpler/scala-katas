package com.kpler.scala.kata.monocle

import cats.FlatMap
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class TypeclassSpecs extends AnyFunSuite with should.Matchers {

  /**
   * Write a function that computes the cartesian product of 2 options
   * If both options are not empty, it should return a new option with a tuple of the 2 values
   * None in any other case
   * You can user either pattern matching or for comprehension
   */
  def cartesianProductOption[A, B](optA: Option[A], optB: Option[B]): Option[(A, B)] = ???

  test("should cartesianProductOption(Option(1), Option(2)) be Some((1,2))") {
    cartesianProductOption(Option(1), Option(2)) shouldBe Some((1, 2))
  }

  test("should cartesianProductOption(None, Option(2)) be None)") {
    cartesianProductOption(None, Option(2)) shouldBe None
  }

  test("should cartesianProductOption(Option(1), None) be None)") {
    cartesianProductOption(Option(1), None) shouldBe None
  }

  /**
   * Write a function that computes the cartesian product of 2 lists
   * the resulting list should contain every tuple (i,j) where i is an element of the first list
   * and j an element of the second one
   */
  def cartesianProductList[A, B](lstA: List[A], lstB: List[B]): List[(A, B)] = ???

  test("should cartesianProductList(List(1, 2), List(2, 3)) be List((1,2), (1,3), (2,2), (2,3))") {
    cartesianProductList(List(1, 2), List(2, 3)) shouldBe List((1, 2), (1, 3), (2, 2), (2, 3))
  }

  test("should cartesianProductList(List(), List(2, 3)) be List()") {
    cartesianProductList(List(), List(2, 3)) shouldBe List()
  }

  test("should cartesianProductList(List(1, 2), List()) be List()") {
    cartesianProductList(List(1, 2), List()) shouldBe List()
  }

  /**
   * If you have written 'cartesianProductOption' and 'cartesianProductList' using for comprehension
   * You might have noticed they are quite identical.
   *
   * The Typeclass pattern is a means to apply polymorphism on Effect types like List and Option
   * For this we define a CartesianProduct Typeclass with a 'tupled' function whose signature looks like
   * 'cartesianProductOption' and 'cartesianProductList' very much but makes abstraction of the Effect type through F
   *
   * The Typeclass pattern is made of 4 steps
   * - The definition of the trait
   * - The definition of the implementations of the trait for concrete types (ie CartesianProduct[Option], CartesianProduct[List], ...)
   * - The definition of the API, usually a function in the companion object
   * - The extension / syntax, to call the api on the Effect type directly: ie Option(1).cartesianProduct(Option(2))
   *
   * We are not going to address the extension / syntax part in this kata
   */
  //1 definition of the trait
  trait CartesianProduct[F[_]] {

    def tupled[A, B](fa: F[A], fb: F[B]): F[(A, B)]

  }

  object CartesianProduct {

    import cats.syntax.all.{toFlatMapOps, toFunctorOps}

    /**
     * As we saw, 'cartesianProductOption' and 'cartesianProductList' are identical if they are written
     * using for comprehension.
     * The idea here is to return an implementation of the CartesianProduct trait for any F which has a FlatMap instance
     * defined.
     * If you are not conformable with the '[F[_] : FlatMap]' notation, keep it mind it is the same as:
     * given cartesianProductInstance[F[_]](using flatmapInstanceForF: FlatMap[F]): CartesianProduct[F]
     *
     * With this FlatMap instance in the scope, you can write for comprehension for any F.
     */
    //2  The definition of the implementations of the trait for concrete types
    given cartesianProductInstance[F[_] : FlatMap]: CartesianProduct[F] = ???

    // api
    def cartesianProduct[F[_], A, B](fa: F[A], fb: F[B])(using instance: CartesianProduct[F]): F[(A, B)] = ???
  }

  test("should cartesianProduct(Option(1), Option(2)) be Some((1,2))") {
    CartesianProduct.cartesianProduct(Option(1), Option(2)) shouldBe Some((1, 2))
  }

  test("should cartesianProduct(None, Option(2)) be None)") {
    CartesianProduct.cartesianProduct(None, Option(2)) shouldBe None
  }

  test("should cartesianProduct(Option(1), None) be None)") {
    CartesianProduct.cartesianProduct(Option(1), None) shouldBe None
  }

  test("should cartesianProduct(List(1, 2), List(2, 3)) be List((1,2), (1,3), (2,2), (2,3))") {
    CartesianProduct.cartesianProduct(List(1, 2), List(2, 3)) shouldBe List((1, 2), (1, 3), (2, 2), (2, 3))
  }

  test("should cartesianProduct(List(), List(2, 3)) be List()") {
    CartesianProduct.cartesianProduct(List(), List(2, 3)) shouldBe List()
  }

  test("should cartesianProduct(List(1, 2), List()) be List()") {
    CartesianProduct.cartesianProduct(List(1, 2), List()) shouldBe List()
  }
}
