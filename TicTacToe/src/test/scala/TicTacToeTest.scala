import org.scalatest._
import flatspec._
import matchers._

import scala.collection.mutable

class TicTacToeTest  extends AnyFlatSpec with should.Matchers {
  "A Tictactoe" should "initialise with the proper grid" in {
    val game = TicTacToe(List())

    game.grid should be (List())
  }
}

case class TicTacToe(val grid: List[String]) {

}
