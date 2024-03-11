import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "A Game" should "consist of 3 rows and 3 columns" in {
    val game = new Game()

    game.cols should have size 3
  }
}
