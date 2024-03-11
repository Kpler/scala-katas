import org.scalatest._
import flatspec._
import matchers._

class GameSpec extends AnyFlatSpec with should.Matchers {

  "A Game" should "consist of 3 columns" in {
    val game = new Game()

    game.cols should have size 3
  }

  "A Game" should "have 3 rows" in {
      val game = new Game()

      game.rows should have size 3
  }

  "A Game" should "have playerX and playerO" in {
    val game = new Game()

    game.playerX.name shouldEqual "X"
    game.playerO.name shouldEqual "O"
  }
}
