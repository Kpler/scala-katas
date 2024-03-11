import org.scalatest._
import flatspec._
import matchers._

class GameSpec extends AnyFlatSpec with should.Matchers {

  "A Game" should "consist of 3 columns" in {
    val game = new Game()

    game.cols should have size 3
  }

    it should "have 3 rows" in {
        val game = new Game()

        game.rows should have size 3
    }

  it should "have playerX and playerO" in {
    val game = new Game()

    game.playerX shouldEqual "X"
    game.playerO shouldEqual "O"
  }
}
