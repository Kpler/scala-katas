import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "the game" should "start" in {
    //While
    val game = new Game()
    game.score() should be (0)
  }


}
