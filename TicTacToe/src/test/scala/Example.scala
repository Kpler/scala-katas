import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "A List" should "drop the first values" in {
    val stack = List(3, 2)
    stack.drop(1) should be (List(2))
  }

  "A New game" should " return a new object" in {
    val game: Game = new Game()
  }
  "A New game" should "have grid field of type dict" in {
    val game: Game = new Game()
    game.grid should be (Map[String,Player]())
  }
}
