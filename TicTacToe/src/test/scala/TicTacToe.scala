import org.scalatest._
import flatspec._
import matchers._

class TestTicTacToe extends AnyFlatSpec with should.Matchers {

  "The app" should " for users input" in {
    // When
    val newGame = new TicTacToe()
    
    // Then
    // check the grid is empty
    newGame.grid should be List(List(), List(), List())
  }

  "The app" should "asks for users input" in {
    // Given
    val newGame = new TicTacToe()

    // When
     
  }
}
