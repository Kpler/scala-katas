import scala.annotation.tailrec
import scala.math.abs

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello Scala FANS !!!")

    // TODO: Implement the singly linked list of MyList in a functional way - using the variadic function syntax
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
case object Nil extends MyList[Nothing] // A `List` data constructor representing the empty list
case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

