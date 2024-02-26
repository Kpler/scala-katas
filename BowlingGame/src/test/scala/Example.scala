import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "the game" should "start" in {
    val game = new Game()
    game.score() should be(0)
  }

  "the game score" should "be incremented when the player rolls" in {
    val game = new Game()
    game.roll(1)
    game.score() should be(1)
  }

  "the spare" should "increment the last roll twice if there is a spare" in {
    val game = new Game()
    game.roll(3)
    game.roll(7)
    // Spare, next roll will be multiplied by 2
    game.roll(4)

    game.score() should be(18) // 3 + 7 + 4 + 4
  }

}
