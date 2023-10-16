import org.scalatest._
import flatspec._
import matchers._

class TennisGameTest extends AnyFlatSpec with should.Matchers {

  "At the start of a game, the score" should "be love-love" in {
    // Given
    val game = TennisGame()
    // When
    val result = game.getScore()
    // Then
    result should be ("love-love")
  }

  "When player 1 score the first point, it" should "return a score of 15-love" in {
    // Given
    val game = TennisGame()
    // When
    val gameWithFirstPoint = game.addOnePointToPlayer1()
    // Then
    gameWithFirstPoint.getScore() should be("15-love")

  }

  "When player 2 score the first point, it" should "return a score of love-15" in {
    // Given
    val game = TennisGame()
    // When
    val gameWithFirstPoint = game.addOnePointToPlayer2()
    // Then
    gameWithFirstPoint.getScore() should be("love-15")
  }

  "When player 1 already scored and player 2 scores, it" should "return 15-15" in {
    // Given
    val game = TennisGame()
    // When
    val gameWithFirstPoint = game.addOnePointToPlayer1()
    val gameWithTwoPoints = gameWithFirstPoint.addOnePointToPlayer2()
    // Then
    gameWithTwoPoints.getScore() should be("15-15")
  }

   "When player 1 scores 2 times than one time, it" should "return 30-love" in {
    // Given
    val game = TennisGame()
    // When
    val gameWithFirstPoint = game.addOnePointToPlayer1()
    val gameWithTwoPoints = gameWithFirstPoint.addOnePointToPlayer1()
    // Then
    gameWithTwoPoints.getScore() should be("30-love")
  }
}

