package freight

import org.scalatest.flatspec._
import org.scalatest.matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    val score: Score = game.score()
    score shouldBe Score("love", "love")
  }

}
class TennisGame() {
  val INITIAL = "love"
  def score(): Score = {
    Score(INITIAL, INITIAL)
  }

}
case class Score(player1: String, player2: String)
