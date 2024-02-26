import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "the game" should "start" in {
    val game = new Game()
    game.score() should be (0)
  }

  "the game score" should "be incremented when the player rolls" in {
    val game = new Game()
    game.roll(1)
    game.score() should be (1)
  }

  "the spare" should "be "

}
