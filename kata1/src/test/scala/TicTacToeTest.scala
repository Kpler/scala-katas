import org.scalatest._
import flatspec._
import matchers._

import scala.collection.immutable.Nil

class TicTacToeTest extends AnyFlatSpec with should.Matchers {
  "TicTacToe" should "be created with a blank grid" in {
    val ticTacToe = TicTacToe()

    ticTacToe.getGrid() should be (Nil)
  }
  "TicTacToe" should "BLABLA" in {
    val ticTacToe = TicTacToe()

//    ticTacToe.getGrid() should be (Nil)
  }
}
