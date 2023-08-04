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

  "In a game with 1 spare" should "return a score of 33" in {
    // Given
    val game = new BowlingGame()
    game.roll(5)
    game.roll(5)
    game.roll(3)
    for (i <- 1 to 17) {
      game.roll(1)
    }
    // When
    val score = game.score()
    // Then
    score should be (33)
  }
}

class BowlingGame() {
  var rolledPins = List[Int]()
  def score() : Int = {

    rolledPins.sum
  }

  def roll(pins: Int) : Unit = {
    this.rolledPins = this.rolledPins :+ pins
  }

}