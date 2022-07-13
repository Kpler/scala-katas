import org.scalatest._
import flatspec._
import matchers._

import scala.List

class TestTicTacToe extends AnyFlatSpec with should.Matchers {

  "The app" should " for users input" in {
    // When
    val newGame = new TicTacToe()
    
    // Then
    // check the grid is empty

    newGame.grid should be 3
  }

  "The app" should "asks for users input" in {
    // Given
    val newGame = new TicTacToe()

    // When
     
  }
}
