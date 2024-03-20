import org.scalatest._
import flatspec._
import matchers._

class FpSpec extends AnyFlatSpec with should.Matchers {
  private def fpFactorial(n: Int): Int = {
    ???
  }

  "you" should "write a function able to compute the factorial of integers up to 2" in {
    val result =  fpFactorial(2)
    result should equal(2)
  }

  "you" should "write a function able to compute the factorial of integers up to 3" in {
    val result =  fpFactorial(3)
    result should equal(6)
  }

  "you" should "write a function able to compute the factorial of integers up to 4" in {
    val result =  fpFactorial(4)
    result should equal(24)
  }

  def findFirst(array: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if (n >= array.length)
        -1
      else if (array(n) == key)
        n
      else loop(n + 1)
    loop(0)
  }

  def findFirst(array: Array[Int], key: Int): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if (n >= array.length)
        -1
      else if (array(n) == key)
        n
      else loop(n + 1)
    loop(0)
  }

  "findFirst" should "become a generic function called genericFindFirst and work for Char" in {
    val chars: Array[Char] = Array('b', 'a', 'c', 'd')

    // Be careful you need to change the signature to pass a function that will test equality
    // get me the index of a
//    genericFindFirst(chars, stuff, fn) should be(1)
  }

  "findFirst" should "become a generic function called genericFindFirst and work for String" in {
    val chars: Array[String] = Array("b", "a", "c", "d")

    // Be careful you need to change the signature to pass a function that will test equality (but create a val function)
    // get me the index of a
//    genericFindFirst(chars, stuff) should be(1)
  }

  def isSorted[A](arr: Array[A], gt: (A, A) => Boolean): Boolean = {
    ???
  }

  "isSorted" should "confirm it is when it s the case for integers" in {
    isSorted(Array(1, 2, 3), (x: Int, y: Int) => x > y) should be(true)
  }

  "isSorted" should "return false when it s NOT the case for integers" in {
    isSorted(Array(2, 1, 3), (x: Int, y: Int) => x > y) should be(false)
  }


  "isSorted" should "confirm it is when it s the case for floats" in {
    isSorted(Array(1.3F, 2.0F, 3F), (x: Float, y: Float) => x > y) should be(true)
  }
}
