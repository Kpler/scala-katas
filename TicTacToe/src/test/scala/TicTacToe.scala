import org.scalatest._
import flatspec._
import matchers._

import scala.List

class TestTicTacToe extends AnyFlatSpec with should.Matchers {

  "TicTacToe" should "have an empty grid on new game" in {
    // When
    val newGame = new TicTacToe()
    
    // Then
    // check the grid has all zero values
    newGame.grid should be (Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0)))

  }

  "TicTacToe" should "have 1 player only" in {
    // When
    val newGame = new TicTacToe()
    
    // Then

  }

}
