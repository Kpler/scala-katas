import org.scalatest._
import flatspec._
import matchers._

class TicTacToeTest extends AnyFlatSpec with should.Matchers {

  "Tictactoe" should "be able to insert at a1" in {
    val tictactoe = TicTacToe()

    val newTictactoe = tictactoe.play("a1")
    newTictactoe.getAt("a1") should be(Some(Cross))
  }

  it should "be able to insert at a1 and a2" in {
    val tictactoe = TicTacToe()
    val newTictactoe = tictactoe.play("a1").play("a2")
    newTictactoe.getAt("a1") should be (Some(Cross))
    newTictactoe.getAt("a2") should be (Some(Circle))
  }
}
