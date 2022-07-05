import org.scalatest._
import flatspec._
import matchers._

class TicTacToeTest extends AnyFlatSpec with should.Matchers {

  "A List" should "drop the first values" in {
    val tictactoe = TicTacToe()

    tictactoe.play("a1")

//    stack.drop(1) should be (List(2))
  }

}
