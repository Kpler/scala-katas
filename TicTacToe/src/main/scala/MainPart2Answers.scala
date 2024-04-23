object MainPart2Answers {
  def main(args: Array[String]): Unit = {
    println("Hello Scala FANS !!!")

    // TODO: Implement the singly linked list of MyList in a functional way - using the variadic function syntax

    // Pattern matching
    // I want that if some variable named stuffList (for instance) of type List of Int has more than 1 element then it returns the first element otherwise it return -1
    val exList = List(1, 2, 3, 4, 5)
    val result = exList match {
      case x :: y :: _ => x
      case _ => -1
    }
    val exListWithOneElement = List(1)
    val result2 = exListWithOneElement match {
      case x :: y :: _ => x
      case _ => -1
    }
    val exListWithNoElements: List[Int] = List()
    val result3 = exListWithNoElements match {
      case x :: y :: _ => x
      case _ => -1
    }
    println(result)
    println(result2)
    println(result3)

  }
}

// Singly linked list
sealed trait MyListAnswer[+A]
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
case object NilAnswer extends MyListAnswer[Nothing] // A `List` data constructor representing the empty list
case class ConsAnswer[+A](head: A, tail: MyListAnswer[A]) extends MyListAnswer[A]

object MyListAnswer {
  def apply[A](as: A*): MyListAnswer[A] =
    if (as.isEmpty) NilAnswer
    else ConsAnswer(as.head, apply(as.tail: _*))
}
