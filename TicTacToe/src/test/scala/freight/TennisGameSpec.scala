package freight

import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {

  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    val score: Score = game.score()
    score shouldBe Score("love", "love")
  }
  "given a starting game when a player score one point then Score" should "be (15,love)" in {
    val game = new TennisGame()
    val score = game.playerOneScore()
    score.score() shouldBe Score("15","love")
  }

}
class TennisGame() {
  def playerOneScore() = new

  val INITIAL = "love"
  def score(): Score = {
    Score(INITIAL, INITIAL)
  }

}
case class Score(player1: String, player2: String)
