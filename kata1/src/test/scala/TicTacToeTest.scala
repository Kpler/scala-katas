import org.scalatest._
import flatspec._
import matchers._

import scala.util.Failure


import scala.collection.immutable.Nil

class TicTacToeTest extends AnyFlatSpec with should.Matchers {
  "TicTacToe" should "be created with a blank grid" in {
    val ticTacToe = TicTacToe()

    ticTacToe.a1 should be (None)
    ticTacToe.a2 should be (None)
    ticTacToe.a3 should be (None)
    ticTacToe.b1 should be (None)
    ticTacToe.b2 should be (None)
    ticTacToe.b3 should be (None)
    ticTacToe.c1 should be (None)
    ticTacToe.c2 should be (None)
    ticTacToe.c3 should be (None)
  }

  "TicTacToe" should "have Cross as 1st player" in {
    val ticTacToe = TicTacToe()

    val res = ticTacToe.play("a1")
    res.get.a1 should be (Some(Cross))
  }

  "TicTacToe" should "have Circle as 2nd player" in {
    val ticTacToe = TicTacToe()

    val res = ticTacToe.play("a1").get.play("c1")
    res.get.c1 should be (Some(Circle))
  }

  "TicTacToe" should "be able to handle players properly" in {
    val ticTacToe = TicTacToe()

    val res = ticTacToe.play("c1").get.play("a1")
    res.get.c1 should be (Some(Cross))
    res.get.a1 should be (Some(Circle))
  }

  "TicTacToe" should "not authorise to play twice the same position in the same game" in {
    val ticTacToe = TicTacToe()

    val res = ticTacToe.play("c1").get.play("c1")
    res should be (Failure(new NotAllowed()))
  }
}
