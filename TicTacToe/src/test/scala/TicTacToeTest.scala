import org.scalatest._
import flatspec._
import matchers._

case class TicTacToe(val grid: List[String] = Nil) {
  // grid = List(9, Nil)
}


class TicTacToeTest extends AnyFlatSpec with should.Matchers {
  "TicTacToe" should "be instantiated with an empty grid" {
    val ticTacToe = TicTacToe()

    ticTacToe.grid.size should be (0)
  }

  // "TicTacToe" should "be instantiated with an empty grid" {
  //   val ticTacToe = TicTacToe()

  //   ticTacToe.grid should be empty
  // }


}

