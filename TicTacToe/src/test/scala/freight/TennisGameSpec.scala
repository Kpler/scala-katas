package freight

import freight.TennisGame.{FIFTEEN, INITIAL, THIRTY}
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {


  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    val score: Score = game.score()
    score shouldBe Score(INITIAL, INITIAL)
  }

  "given a starting game when player one score one point then Score" should "be (15,love)" in {
    val game = new TennisGame()
    game.playerOneScore().score() shouldBe Score(FIFTEEN, INITIAL)
  }

  "given a starting game when player two score one point then Score" should "be (love,15)" in {
    val game = new TennisGame()
    game.playerTwoScore().score() shouldBe Score(INITIAL, FIFTEEN)
  }

  "given a game with score 15-love, when player mark one point then score" should "be 30-love" in {
    val game = new TennisGame()
    game
      .playerOneScore()
      .playerOneScore().score() shouldBe Score(THIRTY, INITIAL)
  }

  "given a game with score 15-love, when player 2 score one point then score" should "be 15-15" in {
    val game = new TennisGame()
    game
      .playerOneScore()
      .playerTwoScore().score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 1 score one point then score" should "be 15-15" in {
    val game = new TennisGame()
    game
      .playerTwoScore()
      .playerOneScore().score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

}
object TennisGame {
  val INITIAL = "love"
  val FIFTEEN = "15"
  val THIRTY = "30"
}
class TennisGame(val scoreInitial: Score = Score(INITIAL, INITIAL)) {
  def playerOneScore(): TennisGame = {
    scoreInitial match {
      case Score(INITIAL,INITIAL) => new TennisGame(Score(FIFTEEN, INITIAL))
      case _ =>
        new TennisGame(Score(THIRTY, INITIAL))
    }
  }

  def playerTwoScore():  TennisGame={
    scoreInitial match {
      case Score(INITIAL, INITIAL) => new TennisGame(Score(INITIAL,FIFTEEN))
      case _ =>
        new TennisGame(Score(FIFTEEN, FIFTEEN))
    }
  }

  def score(): Score = scoreInitial

}
case class Score(player1: String, player2: String)
