package freight

import freight.TennisGame.{FIFTEEN, INITIAL, THIRTHY, THIRTY}
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {

  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    val score: Score = game.score()
    score shouldBe Score("love", "love")
  }

  "given a starting game when player one score one point then Score" should "be (15,love)" in {
    val game = new TennisGame()
    game.playerOneScore().score() shouldBe Score("15", "love")
  }

  "given a starting game when player two score one point then Score" should "be (love,15)" in {
    val game = new TennisGame()
    game.playerTwoScore().score() shouldBe Score("love", "15")
  }

  "given a game with score 15-live, when player mark one point then score" should "be 30-love" in {
    val game = new TennisGame()
    game
      .playerOneScore()
      .playerOneScore().score() shouldBe Score("30", "love")
  }

}
object TennisGame {
  val INITIAL = "love"
  val FIFTEEN = "15"
  val THIRTY = "30"
}
class TennisGame(val scoreInitial: Score = Score(INITIAL, INITIAL)) {
  def playerTwoScore():  TennisGame={new TennisGame(Score(INITIAL,FIFTEEN))}


  def playerOneScore(): TennisGame = {
    scoreInitial match {
      case Score(INITIAL,INITIAL) => new TennisGame(Score(FIFTEEN, INITIAL))
      case _ =>
        new TennisGame(Score(THIRTY, INITIAL))
    }
  }
  def score(): Score = scoreInitial

}
case class Score(player1: String, player2: String)
