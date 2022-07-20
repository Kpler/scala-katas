
import org.scalatest._
import flatspec._
import matchers._

class TicTacToeTest extends AnyFlatSpec with should.Matchers {

  "TicTacToe" should "exists" in {
    val ticTacToe = TicTacToe()
    ticTacToe.grid.length should be (3)
  }
}
