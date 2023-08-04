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
    game.roll(5) // 0
    game.roll(5) // 1
    game.roll(3) // 2
    for (i <- 1 to 17) {
      game.roll(1)
    }
    // When
    val score = game.score()
    // Then
    score should be (33)
  }

  "In a game with 1 strike" should "return a score of 42" in {
    // Given
    val game = new BowlingGame()
    game.roll(10) // 10
    game.roll(5) // 1
    game.roll(3) // 2
    for (i <- 1 to 16) { // TODO
      game.roll(1)
    }
    // When
    val score = game.score()
    // Then
    score should be (42)
  }

  "In a game with 1 spare at the very end" should "return a score of 31" in {
    // Given
    val game = new BowlingGame()

    for (i <- 1 to 18) { // TODO
      game.roll(1)
    }
    game.roll(7) // 7
    game.roll(3) // 3
    game.roll(3) // 3
    // When
    val score = game.score()
    // Then
    score should be (31)
  }
}
