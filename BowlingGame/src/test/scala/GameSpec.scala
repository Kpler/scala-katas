import org.scalatest._
import flatspec._
import matchers._

class GameSpec extends AnyFlatSpec with should.Matchers {
  "beginning of game" should "return score of 0 " in {
    val game = new Game()
    val result  = game.score()
    result should be(0)
  }

  "game after one roll of 1" should "return a score of 1" in {
    val game = new Game()
    game.roll(1)

    val result = game.score()
    result should be(1)
  }

  "full game" should "have 10 turns?" in {}

  // TODO: manage spares, manage strikes, then manage checks below:
  // "one roll" should "should not be greater than 10" in {}
  // "sum of rolls in one turn" should "should not be greater than 10" in {}
}


class Game {
  var currentScore: Int = 0

  def roll(pins: Int): Unit = {
    currentScore += pins
  }

  def score(): Int = {
    currentScore
  }
}