package freight

import freight.TennisGame._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {
  val game = new TennisGame()
  val gameLoveFifteen = game
    .playerTwoScore()
    val gameFifteenLove = game
      .playerOneScore()
  val gameThirtyLove: TennisGame =
    gameFifteenLove
    .playerOneScore()
  private val gameFortyLove: TennisGame = gameThirtyLove
    .playerOneScore()
  private val gameFortyForty: TennisGame = gameFortyLove
    .playerTwoScore()
    .playerTwoScore()
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
    gameFifteenLove
      .playerOneScore()
      .score() shouldBe Score(THIRTY, LOVE)
  }

  "given a game with score 15-love, when player 2 score one point then score" should "be 15-15" in {
    gameFifteenLove
      .playerTwoScore()
      .score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 1 scores one point then score" should "be 15-15" in {
    gameLoveFifteen
      .playerOneScore()
      .score() shouldBe Score(FIFTEEN, FIFTEEN)
  }

  "given a game with score love-15, when player 2 scores one point then score" should "be love-30" in {
    gameLoveFifteen
      .playerTwoScore()
      .score() shouldBe Score(LOVE, THIRTY)
  }

  "given a game with score thirty-love, when player 2 scores one point then score" should "be thirty-fifteen" in {
    gameThirtyLove
      .playerTwoScore()
      .score() shouldBe Score(THIRTY, FIFTEEN)
  }

  "given a game with score thirty-love, when player 1 scores one point then score" should "be forty-love" in {
    gameThirtyLove
      .playerOneScore()
      .score() shouldBe Score(FORTY, LOVE)
  }

  "given a game with score love-thirty, when player 2 scores one point then score" should "be love-forty" in {
    game
      .playerTwoScore()
      .playerTwoScore()
      .playerTwoScore()
      .score() shouldBe Score(LOVE, FORTY)

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
    gameFortyLove
      .playerOneScore()
      .score() shouldBe Score(WIN, LOVE)
  }

  "given a game with score love-forty, when player 2 scores one point then score" should "be player wins" in {
    game
      .playerTwoScore()
      .playerTwoScore()
      .playerTwoScore()
      .playerTwoScore()
      .score() shouldBe Score(LOVE, WIN)
  }

  "given a game with score forty-forty then score" should "be forty-forty (not deuce-deuce)" in {
    gameFortyForty
      .score() shouldBe Score(FORTY, FORTY)
  }

  "given a game with score forty-forty then play 1 scores" should "be adv-forty" in {
    gameFortyForty
      .playerOneScore()
      .score() shouldBe Score(ADV, FORTY)
  }
  "given a game with score forty-forty then play 2 scores" should "be forty-adv" in {
    gameFortyForty
      .playerTwoScore()
      .score() shouldBe Score(FORTY, ADV)
  }
  "given a game with score adv-forty then play 1 scores" should "be player 1 wins" in {
    gameFortyForty.playerOneScore()
      .playerOneScore()
      .score() shouldBe Score(WIN, FORTY)
  }
  "given a game with score forty-adv then play 2 scores" should "be player 2 wins" in {
    gameFortyForty
      .playerTwoScore().playerTwoScore()
      .score() shouldBe Score(FORTY, WIN)
  }

  "given a game with score forty-adv then play 1 scores" should "be forty-forty" in {
    gameFortyForty.playerTwoScore()
      .playerOneScore()
      .score() shouldBe Score(FORTY, FORTY)
  }

  "given a game with score adv-forty then play 2 scores" should "be forty-forty" in {
    gameFortyForty.playerOneScore()
      .playerTwoScore()
      .score() shouldBe Score(FORTY, FORTY)
  }
}
object TennisGame {
  val LOVE = "love"
  val FIFTEEN = "15"
  val THIRTY = "30"
  val FORTY = "40"
  val WIN = "player wins"
  val ADV = "adv"
}
case class TennisGame(val currentScore: Score = Score(LOVE, LOVE)) {
  def nextValue(currentValue: String): String = currentValue match {
    case LOVE => FIFTEEN
    case FIFTEEN => THIRTY
    case THIRTY => FORTY
    case FORTY => WIN
  }

  def playerOneScore(): TennisGame = {
    currentScore match {
      case Score(FORTY, FORTY) => TennisGame(Score(ADV, FORTY))
      case Score(ADV, FORTY) => TennisGame(Score(WIN, FORTY))
      case Score(FORTY, ADV) => TennisGame(Score(FORTY, FORTY))
      case _ => TennisGame(Score(nextValue(currentScore.player1), currentScore.player2))
    }

  }

  def playerTwoScore(): TennisGame = {
    currentScore match {
      case Score(FORTY, FORTY)=> TennisGame(Score(FORTY, ADV))
      case Score(FORTY, ADV)=> TennisGame(Score(FORTY, WIN))
      case Score(ADV, FORTY) => TennisGame(Score(FORTY, FORTY))
      case  _ => TennisGame(Score(currentScore.player1, nextValue(currentScore.player2)))
    }
  }

  def score(): Score = currentScore

}
case class Score(player1: String, player2: String)
