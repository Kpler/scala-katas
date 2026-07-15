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

  "full game" should "have 10 frames" in {
    val game = new Game()
    for (i <- 0 until 20) {
      game.roll(1);
    }
    val result = game.score()
    result should be(20)
  }

  "full game" should "not have more than 10 frames" in {
    val game = new Game()
    for (i <- 0 until 20) {
      game.roll(1);
    }
    assertThrows[IllegalArgumentException] {
      game.roll(1)
    }
  }

  "a spare" should "compute the score properly" in {
    val game = new Game()
    game.roll(7)
    game.roll(3)
    game.roll(4)

    game.score() should be (18)
  }

  "a game" should "compute the double the next roll after a spare" in {
    val game = new Game()
    game.roll(7)
    game.roll(3)
    game.roll(7)
    game.roll(1)

    game.score() should be (25)
  }

  "a game" should "compute a strike" in {
    val game = new Game()
    game.roll(10)
    game.roll(3)
    game.roll(4)

    game.score() should be (24)
  }

  "a game" should "compute a 0-10 spare" in {
    val game = new Game()
    game.roll(0)
    game.roll(10)
    game.roll(3)
    game.roll(4)

    game.score() should be (20)
  }




}
