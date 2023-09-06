import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "At the start of a game, the score" should "be love-love" in {
    // Given
    val game = TennisGame()
    // When
    val result = game.getScore()
    // Then
    result should be ("love-love")
  }

  "When player 1 score the first point, it" should "return a score of 15-0" in {

  }

  "When player 2 score the first point, it" should "return a score of 0-15" in {

  }
}

case class TennisGame() {
  def getScore() : String = ???

}