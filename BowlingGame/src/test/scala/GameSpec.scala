import org.scalatest._
import flatspec._
import matchers._

class GameSpec extends AnyFlatSpec with should.Matchers {

  "beginning of game" should "return score of 0 " in {
    val game = new Game()
    val tested  = game.score()
    tested should be(0)
  }

  "a game with a pin 1 " should "return 10" in {
    val game = new Game()
    game.roll(1)

    val tested = game.score()

  }


}

class Game {
  def roll(pin : Int): Unit = {
    ???
  }

  def score() : Int = {
    0
  }
}