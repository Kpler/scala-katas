package freight

import freight.TennisGame.{FIFTEEN, FORTY, LOVE, THIRTY}
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {
  val game = new TennisGame()
  val gameLoveFifteen = game
    .playerTwoScore()
  "a starting game" should "have score set to love on both side" in {

    val score: Score = game.score()
    score shouldBe Score(LOVE, LOVE)
  }

  "given a starting game when player one score one point then Score" should "be (15,love)" in {
    game.playerOneScore().score() shouldBe Score(FIFTEEN, LOVE)
  }

  "given a starting game when player two score one point then Score" should "be (love,15)" in {
    gameLoveFifteen.score() shouldBe Score(LOVE, FIFTEEN)
  }

  "given a game with score 15-love, when player mark one point then score" should "be 30-love" in {
    game
      .playerOneScore()
      .playerOneScore().score() shouldBe Score(THIRTY, LOVE)
  }

  "given a game with score 15-love, when player 2 score one point then score" should "be 15-15" in {
    game
      .playerOneScore()
      .playerTwoScore().score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 1 scores one point then score" should "be 15-15" in {

    gameLoveFifteen
      .playerOneScore().score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 2 scores one point then score" should "be love-30" in {
    gameLoveFifteen
      .playerTwoScore().score() shouldBe Score(LOVE, THIRTY)
  }

  "given a game with score thirty-love, when player 2 scores one point then score" should "be thirty-fifteen" in {
    game
      .playerOneScore().playerOneScore().playerTwoScore().score() shouldBe Score(THIRTY, FIFTEEN)

  }

  "given a game with score thirty-love, when player 1 scores one point then score" should "be forty-love" in {
    game
      .playerOneScore().playerOneScore().playerOneScore().score() shouldBe Score(FORTY, LOVE)

  }

  "given a game with score love-thirty, when player 2 scores one point then score" should "be love-forty" in {
    game
      .playerTwoScore().playerTwoScore().playerTwoScore().score() shouldBe Score(LOVE, FORTY)

  }

  "given a value of love, nextvalue" should "be FIFTEEN" in {
    game.nextValue(LOVE) shouldBe FIFTEEN
  }

  "given a value of fifteen, nextvalue" should "be thirty" in {
    game.nextValue(FIFTEEN) shouldBe THIRTY
  }

  "given a value of thirty, nextvalue" should "be thirty" in {
    game.nextValue(FIFTEEN) shouldBe THIRTY
  }
}
object TennisGame {
  val LOVE = "love"
  val FIFTEEN = "15"
  val THIRTY = "30"
  val FORTY = "40"
}
class TennisGame(val currentScore: Score = Score(LOVE, LOVE)) {
  def nextValue(currentValue: String) = currentValue match {
    case LOVE => FIFTEEN
    case _ => THIRTY
  }


  def playerOneScore(): TennisGame = {
    currentScore match {
      case Score(LOVE, value) => new TennisGame(Score(FIFTEEN, value))
      case Score(THIRTY, LOVE) => new TennisGame(Score(FORTY, LOVE))
      case _ =>
        new TennisGame(Score(THIRTY, LOVE))
    }
  }

  def playerTwoScore():  TennisGame={
    currentScore match {
      case Score(value, LOVE) => new TennisGame(Score(value, FIFTEEN))
      case Score(value,FIFTEEN) => new TennisGame(Score(value,THIRTY))
      case Score(value,THIRTY) => new TennisGame(Score(value,FORTY))
    }
  }

  def score(): Score = currentScore

}
case class Score(player1: String, player2: String)
