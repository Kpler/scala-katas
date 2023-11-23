package freight

import freight.TennisGame.{FIFTEEN, LOVE, THIRTY}
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {


  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    val score: Score = game.score()
    score shouldBe Score(LOVE, LOVE)
  }

  "given a starting game when player one score one point then Score" should "be (15,love)" in {
    val game = new TennisGame()
    game.playerOneScore().score() shouldBe Score(FIFTEEN, LOVE)
  }

  "given a starting game when player two score one point then Score" should "be (love,15)" in {
    val game = new TennisGame()
    game.playerTwoScore().score() shouldBe Score(LOVE, FIFTEEN)
  }

  "given a game with score 15-love, when player mark one point then score" should "be 30-love" in {
    val game = new TennisGame()
    game
      .playerOneScore()
      .playerOneScore().score() shouldBe Score(THIRTY, LOVE)
  }

  "given a game with score 15-love, when player 2 score one point then score" should "be 15-15" in {
    val game = new TennisGame()
    game
      .playerOneScore()
      .playerTwoScore().score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 1 scores one point then score" should "be 15-15" in {
    val game = new TennisGame()
    game
      .playerTwoScore()
      .playerOneScore().score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 2 scores one point then score" should "be love-30" in {
    val game = new TennisGame()
    game
      .playerTwoScore()
      .playerTwoScore().score() shouldBe Score(LOVE, THIRTY)
  }

  "given a game with score thirty-love, when player 2 scores one point then score" should "be thirty-fifteen" in {
    val game = new TennisGame()
    game
      .playerOneScore().playerOneScore().playerTwoScore().score() shouldBe Score(THIRTY, FIFTEEN)

  }
}
object TennisGame {
  val LOVE = "love"
  val FIFTEEN = "15"
  val THIRTY = "30"
}
class TennisGame(val scoreInitial: Score = Score(LOVE, LOVE)) {
  def playerOneScore(): TennisGame = {
    scoreInitial match {
      case Score(LOVE, value) => new TennisGame(Score(FIFTEEN, value))
      case _ =>
        new TennisGame(Score(THIRTY, LOVE))
    }
  }

  def playerTwoScore():  TennisGame={
    scoreInitial match {
      case Score(value, LOVE) => new TennisGame(Score(value, FIFTEEN))
      case Score(value,FIFTEEN) => new TennisGame(Score(value,THIRTY))
      case _ =>
        new TennisGame(Score(FIFTEEN, FIFTEEN))
    }
  }

  def score(): Score = scoreInitial

}
case class Score(player1: String, player2: String)
