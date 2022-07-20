import org.scalatest._
import flatspec._
import matchers._

case class TicTacToe(val grid: List[String] = Nil) {

}


class TicTacToeTest extends AnyFlatSpec with should.Matchers {
  "TicTacToe" should "be instantiated with an empty grid" {
    val ticTacToe = TicTacToe()

    ticTacToe.grid should be (Nil)
  }
}

