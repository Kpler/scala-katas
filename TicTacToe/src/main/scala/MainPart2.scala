import scala.annotation.tailrec
import scala.math.abs

object MainPart2 {

  def main(args: Array[String]): Unit = {
    println("Hello Scala FANS !!!")

    // Todo: Implement the singly linked list of MyList in a functional way - using the variadic function syntax

    // Pattern matching
    // Todo: I want that if some variable named stuffList (for instance) of type List of Int has more than 1 element
    // then it returns the first element otherwise it return -1

    // Todo: Let's do it again with our MyList
  }
}

// Singly linked list
sealed trait MyList[+A]
/*
  In Scala, the notation +A within square brackets ([+A])
  is used to denote that the type parameter A is covariant.
  Covariance means that if B is a subtype of A, then MyList[B] is considered a subtype of MyList[A].

  So when you declare MyList[+A], it means that if B is a subclass of A,
  then MyList[B] is a subclass of MyList[A].

  This allows for more flexible use of types in certain situations,
  particularly when dealing with inheritance and type hierarchies.

  source: chatGPT
 */
case object MyNil extends MyList[Nothing] // A `List` data constructor representing the empty list
case class MyCons[+A](head: A, tail: MyList[A]) extends MyList[A]

