import org.scalatest._
import flatspec._
import matchers._

class BowlingGameSpec extends AnyFlatSpec with should.Matchers {

  "An empty game" should "return a score of 0" in {
    // Given
    val game = new BowlingGame()
    for (i <- 1 to 20) {
      game.roll(0)
    }
    // When
    val score = game.score()
    // Then
    score should be (0)
  }

  "A normal game" should "return a score of 20" in {
    // Given
    val game = new BowlingGame()
    for (i <- 1 to 20) {
      game.roll(1)
    }
    // When
    val score = game.score()
    // Then
    score should be (20)
  }
}

class BowlingGame() {
  var pinsList = List[Int]()
  def score() : Int = {
    0
  }

  def roll(pins: Int) : Unit = {
    this.pinsList = this.pinsList :+ pins
  }

}