import TennisGame.addOnePointToPlayer
import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class TennisGameSpec extends AnyFlatSpec with should.Matchers {

  "At the start of a game, the score" should "be love-love" in {
    // Given
    val game = TennisGame()
    // When
    val result = game.getScore
    // Then
    result should be ("love-love")
  }

  "When player 1 score the first point, it" should "return a score of 15-love" in {
    // Given
    val game = TennisGame()
    // When
    val gameWithFirstPoint: Either[String, TennisGame] = game.addOnePointToPlayer1()
    // Then
    gameWithFirstPoint.right.get.getScore should be("15-love")

  }

  "When player 2 score the first point, it" should "return a score of love-15" in {
    // Given
    val game = TennisGame()
    // When
    val gameWithFirstPoint: Either[String, TennisGame] = game.addOnePointToPlayer2()
    // Then
    gameWithFirstPoint.right.get.getScore should be("love-15")
  }

  "When player 1 already scored and player 2 scores, it" should "return 15-15" in {
    // Given
    val game = TennisGame()
    // When
//    val gameWithFirstPoint = game.addOnePointToPlayer1()
//    val gameWithTwoPoints: Either[String, TennisGame] = gameWithFirstPoint.right.get.addOnePointToPlayer2()
    val gameResult = for {
      gameWithFistPoints <- game.addOnePointToPlayer1()
      gameWithTwoPoints <- gameWithFistPoints.addOnePointToPlayer1()
    } yield gameWithTwoPoints
    // Then
    gameResult.right.get.getScore should be("15-15")
  }
//
//   "When player 1 scores 2 times, it" should "return 30-love" in {
//    // Given
//    val game = TennisGame()
//    // When
//    val gameWithTwoPoints = game.addOnePointToPlayer1().addOnePointToPlayer1()
//    // Then
//    gameWithTwoPoints.right.get.getScore should be("30-love")
//  }
//
//   "When player 1 scores 3 times, it" should "return 40-love" in {
//    // Given
//    val game = TennisGame()
//    // When
//    val gameWithThreePoints = game.addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1()
//    // Then
//     gameWithThreePoints.right.get.getScore should be("40-love")
//  }
//
//  "When player 1 scored 4 times, it" should "return win-love" in {
//    // Given
//    val game = TennisGame()
//    // When
//    val gameWithFourPoints = game.addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1()
//    // Then
//    gameWithFourPoints.right.get.getScore should be("win-love")
//  }
//
//  "When both players reach 40 points, it" should "return deuce" in {
//    // Given
//    val game = TennisGame()
//    // When
//    val gameWithThreePointsPlayerOne: TennisGame = game.addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1()
//
//    (addOnePointToPlayer andThen addOnePointToPlayer andThen addOnePointToPlayer)(game)
//    addOnePointToPlayer(addOnePointToPlayer(addOnePointToPlayer(game)))
//
//
//    val gameWithThreePointsForBoth: TennisGame = gameWithThreePointsPlayerOne.addOnePointToPlayer2().addOnePointToPlayer2().addOnePointToPlayer2()
//    // Then
//    gameWithThreePointsForBoth.getScore should be("deuce")
//  }
//
//  "When both players reach 40 points and Player 1 score, it" should "return win" in {
//    // Given
//    val game = TennisGame()
//    // When
//    val gameWithThreePointsPlayerOne: TennisGame = game.addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1()
//    val gameWithThreePointsForBoth: TennisGame = gameWithThreePointsPlayerOne.addOnePointToPlayer2().addOnePointToPlayer2().addOnePointToPlayer2()
//    val gameWin = gameWithThreePointsForBoth.addOnePointToPlayer1()
//    // Then
//    gameWin.getScore should be("win")
//  }
//
//  "When both players reach 40 points and Player 2 score, it" should "return win" in {
//    // Given
//    val game = TennisGame()
//    // When
//    val gameWithThreePointsPlayerOne: TennisGame = game.addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1()
//    val gameWithThreePointsForBoth: TennisGame = gameWithThreePointsPlayerOne.addOnePointToPlayer2().addOnePointToPlayer2().addOnePointToPlayer2()
//    val gameWin = gameWithThreePointsForBoth.addOnePointToPlayer2()
//    // Then
//    gameWin.getScore should be("win")
//  }
//
//  "Where player one wins and player two scores again, it" should "return false" in {
//    // Given
//    val game = TennisGame()
//
//    // When
//    val gameWithThreePointsPlayerOne: TennisGame = game.addOnePointToPlayer1().addOnePointToPlayer1().addOnePointToPlayer1()
//    val gameWithThreePointsForBoth: TennisGame = gameWithThreePointsPlayerOne.addOnePointToPlayer2().addOnePointToPlayer2().addOnePointToPlayer2()
//    val gameWin = gameWithThreePointsForBoth.addOnePointToPlayer2()
//    val gameFail = gameWin.addOnePointToPlayer2()
//
//    gameFail.isValid should be(false)
//  }

  "When we have a new tennis game, it" should "be valid" in {
    // Given
    val game = TennisGame()
    // Then
    game.isValid should be(true)
  }
}

