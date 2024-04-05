import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class GameSpec extends AnyFlatSpec with should.Matchers {

  "A Game" should "have 3 rows" in {
    val game = new Game()

    game.rows should have size 3
  }

  "A Game" should "have playerX and playerO" in {
    val game = new Game()

    game.playerX.name shouldEqual "X"
    game.playerO.name shouldEqual "O"
  }

  "A new game" should "have its rows with index as value" in {

    val game = new Game()
    game.rows should equal(List(List("1", "2", "3"), List("4", "5", "6"), List("7", "8", "9")))

  }

  "PlayerX plays the first move in the first cell, it" should "have a mark X in the first cell" in {
    val game = Game()

    val newGame = game.playXPlay(0)

    newGame.rows should equal(List(List("X", "2", "3"), List("4", "5", "6"), List("7", "8", "9")))
  }

  "PlayerO plays after PlayerX and " should "have O in the second cell " in {
    val game = Game()
    val newGame: Game = game.playXPlay(0).play0Play(1)

    newGame.rows should equal(List(List("X", "0", "3"), List("4", "5", "6"), List("7", "8", "9")))
  }

  "when PlayerX plays in an occupied cell, it "should "raise an error" in {
    val game = Game()

    assertThrows[RuntimeException] {
      game.playXPlay(0).play0Play(0)
    }
  }

 "when PlayerX plays in the fourth cell" should "have a mark X in the fourth cell" in {
    val game = Game()

    val newGame = game.playXPlay(3)

    newGame.rows should equal(List(List("1", "2", "3"), List("X", "5", "6"), List("7", "8", "9")))
  }

  "play in first cell it " should "translate to row 0" in {
    val game = Game()
    val rowIndex: Int = game.getRowIndex(cell=0)
    rowIndex should be(0)
  }

  "play in first cell it " should "translate to col 0" in {
    val game = Game()
    val colIndex : Int = game.getColIndex(cell=0)
    colIndex should be(0)
  }

  "play in fourth cell it " should "translate to row 1" in {
    val game = Game()
    val rowIndex: Int = game.getRowIndex(cell=3)
    rowIndex should be(1)
  }
  "play in fourth cell it " should "translate to col 1" in {
    val game = Game()
    val colIndex: Int = game.getColIndex(cell=3)
    colIndex should be(0)
  }
  "play in 9 cell it " should "translate to row 2" in {
    val game = Game()
    val rowIndex: Int = game.getRowIndex(cell=8)
    rowIndex should be(2)
  }
  "the game is over when all cell are taken" should "throw game over exception" in{

      assertThrows[RuntimeException] {
        Game.fromBoard(List(List("X","O","X"), List("O","X","O"), List("X", "O", "X")))
      }
  }
}
