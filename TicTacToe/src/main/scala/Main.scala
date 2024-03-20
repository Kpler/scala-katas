import scala.annotation.tailrec
import scala.math.{ abs, max }

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello Scala FANS !!!")

    // Function literals and what not: EXAMPLES
    println("### isLessThan ###")
    println(FirstProgram.isLessThan(2, 3))

    println("### isLessThan2 ###")
    println(FirstProgram.isLessThan2.apply(2, 3))

    println("### isLessThan2 but without apply")
    println(FirstProgram.isLessThan2(2, 3))

    println("### isLessThan3 ###")
    println(FirstProgram.isLessThan3(2, 3))

    // Todo: what does the warning tell us?
    // Todo: Dive into Function2. What does the class hierarchy tell us?

    // Inner function
    // Todo: Write a factorial function in FpSpec using recursive call. What did the compiler teach you?

    // Higher order functions
    SecondProgram.printAbsAndFactorial()

    // todo: write a public format function that will take a string, the integer and a function
    // println(FirstProgram.yourHigherOrderFunction(the string (absolute value / factorial), the integer, and the function))

    // Todo: Go make some generic function in FpSpec

    // Todo: Go make isSorted tests work, tips: higher order function all the way

    // function composition:
    val sin: Double => Double = (x: Double) => Math.sin(x)
    val transf: Double => Double = (x: Double) => (Math.PI / 2) - x
    val fn: Double => Double = x => sin(transf(x))
    val fnWithAndThen: Double => Double = x => (transf andThen sin)(x)
    val fnWithCompose: Double => Double = x => (sin compose transf)(x)

    println("### function composition ###")
    println(fn(0.2d))
    println(fnWithAndThen(0.2d))
    println(fnWithCompose(0.2d))
  }
}

object FirstProgram {
  val isLessThan = (x: Int, y: Int) => x < y
  val isLessThan2 = new Function2[Int, Int, Boolean] {
    def apply(x: Int, y: Int): Boolean = x < y
  }

  def isLessThan3(x: Int, y: Int): Boolean = x < y

}

object SecondProgram {
  def factorial(n: Int): Int = {
    @tailrec
    def go(n: Int, acc: Int): Int = {
      if (n <= 0)
        acc
      else go(n - 1, n * acc)
    }
    go(n, 1)
  }

  def formatAbs(x: Int): String = {
    val msg = "The absolute value of %d is %d."
    msg.format(x, abs(x))
  }

  def formatFactorial(n: Int): String = {
    val msg = "The factorial of %d is %d."
    msg.format(n, factorial(n))
  }

  def printAbsAndFactorial(): Unit = {
    println(formatAbs(-42))
    println(formatFactorial(7))
  }

}
