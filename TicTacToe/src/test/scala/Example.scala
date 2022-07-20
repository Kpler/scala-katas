import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "A move" should "return an updated grid" in {
    val game = List()

    val test = Manager.move(game, (0, 0))

    test.right.get should have size 1

  }

  "an invalid move" should "return an error" in {
    val game = List()

    val test = Manager.move(game, (42, 42))

    test.left.get should be ("invalid move")
  }


}


object Manager {
  def move(list: List[(Int, Int)], move: (Int, Int)): Either[String, List[(Int, Int)]] = {
    move match:
      case (0, 0): Right(list :+ move)
      case (0, 1): Right(list :+ move)
      case (0, 2): Right(list :+ move)
      case (1, 0): Right(list :+ move)
      case (1, 1): Right(list :+ move)
      case (1, 2): Right(list :+ move)
      case (2, 0): Right(list :+ move)
      case (2, 1): Right(list :+ move)
      case (2, 2): Right(list :+ move)
      case _: Left("invalid move")
  }
}
