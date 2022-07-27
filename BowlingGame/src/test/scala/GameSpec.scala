import org.scalatest._
import flatspec._
import matchers._

import org.company.module

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

  "full game" should "have 10 frames" in {
    val game = new Game();
    for (i <- 0 until 20) {
      game.roll(1);
    }
    val result = game.score()
    result should be(20)
  }

  "full game" should "not have more than 10 frames" in {
    val game = new Game();
    for (i <- 0 until 20) {
      game.roll(1);
    }
    assertThrows[IllegalArgumentException] {
      game.roll(1)
    }
  }

  // TODO: manage spares, manage strikes, then manage checks below:
  // "one roll" should "should not be greater than 10" in {}
  // "sum of rolls in one turn" should "should not be greater than 10" in {}
}
