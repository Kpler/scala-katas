import org.scalatest._
import flatspec._
import matchers._

case class TicTacToe(val grid: List[String] = List(null, null, null, null, null, null, null, null, null) ) {

}


class TicTacToeTest extends AnyFlatSpec with should.Matchers {
  "TicTacToe" should "be instantiated with an 9 empty elements" in {
    val ticTacToe = TicTacToe()

    ticTacToe.grid.size should be (9)
    ticTacToe.grid.forall(_ == null) should be (true)
  }

  "TicTacToe" should "store a move" in {
    val ticTacToe = TicTacToe()
    ticTacToe.grid.forall(_ == null) should be (true)
  }

}

