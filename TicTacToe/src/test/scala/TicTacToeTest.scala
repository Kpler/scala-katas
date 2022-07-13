import org.scalatest._
import flatspec._
import matchers._

import scala.collection.mutable

class TicTacToeTest  extends AnyFlatSpec with should.Matchers {
  "A Tictactoe" should "initialise with the empty grid" in {
    val game = TicTacToe(List())

    game.grid should be (List())
  }

  "A Player" should "be able to take a cell" in {
    val game = TicTacToe(List())
    val result = game.play("b3")

    result.grid should have size 1
    result.grid should contain ("b3")
  }

    "A Player" should "not be able to take an occupied cell" in {
    val game = TicTacToe(List())
    val result = game.play("b3").play("b3")

    result.grid should have size 1
  }
}

case class TicTacToe(val grid: List[String]) {
  def play(position: String): TicTacToe = {
    TicTacToe(grid :+ position)
  }
}
