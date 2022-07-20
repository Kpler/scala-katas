
import org.scalatest._
import flatspec._
import matchers._

class TicTacToeTest extends AnyFlatSpec with should.Matchers {

  "TicTacToe" should "exists" in {
    val ticTacToe = TicTacToe()
    ticTacToe.grid.length should be (3)
    ticTacToe.grid(0).length should be (3)
    ticTacToe.grid(1).length should be (3)
    ticTacToe.grid(2).length should be (3)
  }

  "TicTacToe" should "be initialized empty" in {
    val ticTacToe = TicTacToe()
    ticTacToe.grid(0)(0) should be (None)
    ticTacToe.grid(0)(1) should be (None)
    ticTacToe.grid(0)(2) should be (None)
    ticTacToe.grid(1)(0) should be (None)
    ticTacToe.grid(1)(1) should be (None)
    ticTacToe.grid(1)(2) should be (None)
    ticTacToe.grid(2)(0) should be (None)
    ticTacToe.grid(2)(1) should be (None)
    ticTacToe.grid(2)(2) should be (None)
  }

  "TicTacToe" should "allow to play" in {
    val ticTacToe = TicTacToe()
    val newTicTacToe = ticTacToe.play(1, List(0, 0))

    newTicTacToe.grid(0)(0) should be (1)

  }
}
