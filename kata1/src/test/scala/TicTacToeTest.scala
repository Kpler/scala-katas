import org.scalatest._
import flatspec._
import matchers._

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
    res.a1 should be (Some(Cross))
  }
}
