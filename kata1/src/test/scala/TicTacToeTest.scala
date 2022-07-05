import org.scalatest._
import flatspec._
import matchers._

class TicTacToeTest extends AnyFlatSpec with should.Matchers {

  "Tictactoe" should "be able to insert at a1" in {
    val tictactoe = TicTacToe()
    val newTictactoe = tictactoe.play("a1")
    newTictactoe.grid.getAt("a1") should be None
  }
}
