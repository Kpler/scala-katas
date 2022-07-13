import org.scalatest._
import flatspec._
import matchers._

import scala.collection.mutable
import scala.util.Failure

class TicTacToeTest  extends AnyFlatSpec with should.Matchers {
  "A Tictactoe" should "initialise with the empty grid" in {
    val game = TicTacToe()

    game.grid should have size 0
  }

  "A Player" should "be able to take a cell" in {
    val game = TicTacToe()
    val result = game.play("b3")

    result.right.get.grid should have size 1
    result.right.get.grid should contain ("b3")
  }

  "A Player" should "not be able to take an occupied cell" in {
    val game = TicTacToe()
    val result = game.play("b3").right.get.play("b3")

    result should be (Left("Already contained position"))
  }

  "A Player" should "be only able to play in a grid of 3x3 cells " in{
     val game = TicTacToe()

     val result = game.play("b4")

    result should be (Left("Invalid position"))
  }

}

