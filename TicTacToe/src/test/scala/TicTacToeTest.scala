import org.scalatest._
import flatspec._
import matchers._

case class TicTacToe(val grid: List[String] = List(null, null, null, null, null, null, null, null, null) ) {
  def play(position: String): TicTacToe = {
    return this
  }
}

// index 0 = a1
// index 1 = a2
// index 2 = a3
// index 3 = b4


class TicTacToeTest extends AnyFlatSpec with should.Matchers {
  "TicTacToe" should "be instantiated with an 9 empty elements" in {
    val ticTacToe = TicTacToe()

    ticTacToe.grid.size should be (9)
    ticTacToe.grid.forall(_ == null) should be (true)
  }

  "TicTacToe" should "store a move" in {
    val ticTacToe = TicTacToe()
    val newTicTacToe = ticTacToe.play("a1")

    newTicTacToe.grid.get(0) should be ("x")
  }

}

