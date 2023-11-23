package freight

import freight.TennisGame.{FIFTEEN, FORTY, LOVE, THIRTY, WIN}
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

  "given a value of love, nextValue" should "be FIFTEEN" in {
    game.nextValue(LOVE) shouldBe FIFTEEN
  }

  "given a value of fifteen, nextValue" should "be thirty" in {
    game.nextValue(FIFTEEN) shouldBe THIRTY
  }

  "given a value of thirty, nextValue" should "be forty" in {
    game.nextValue(THIRTY) shouldBe FORTY
  }


  "given a game with score forty-love, when player 1 scores one point then score" should "be player wins" in {
    game
      .playerOneScore().playerOneScore().playerOneScore().playerOneScore().score() shouldBe Score(WIN, LOVE)
  }

  "given a game with score love-forty, when player 2 scores one point then score" should "be player wins" in {
    game
      .playerTwoScore().playerTwoScore().playerTwoScore().playerTwoScore().score() shouldBe Score(LOVE, WIN)
  }

  "given a game with score forty-forty then score" should "be forty-forty (not deuce-deuce)" in {
    game
      .playerOneScore().playerOneScore().playerOneScore()
      .playerTwoScore().playerTwoScore().playerTwoScore()
      .score() shouldBe Score(FORTY, FORTY)
  }


}
object TennisGame {
  val LOVE = "love"
  val FIFTEEN = "15"
  val THIRTY = "30"
  val FORTY = "40"
  val WIN = "player wins"
}
class TennisGame(val currentScore: Score = Score(LOVE, LOVE)) {
  def nextValue(currentValue: String) = currentValue match {
    case LOVE => FIFTEEN
    case FIFTEEN => THIRTY
    case THIRTY => FORTY
    case FORTY => WIN
  }


  def playerOneScore(): TennisGame = {
    new TennisGame(Score(nextValue(currentScore.player1), currentScore.player2 ))
  }

  def playerTwoScore():  TennisGame = {
    new TennisGame(Score(currentScore.player1, nextValue(currentScore.player2)))
  }

  def score(): Score = currentScore

}
case class Score(player1: String, player2: String)
