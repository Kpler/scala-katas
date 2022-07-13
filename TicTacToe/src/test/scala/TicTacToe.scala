import org.scalatest._
import flatspec._
import matchers._

class TestTicTacToe extends AnyFlatSpec with should.Matchers {

  "The app" should "asks for users input" in {
    // Given
    val newGame = new TicTacToe()

    // When
    Main.main()
  }
}
