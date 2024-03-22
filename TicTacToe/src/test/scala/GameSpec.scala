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
    val game = new Game()

    val newGame = game.playXPlay(0)

    game.rows should equal(List(List("X", "2", "3"), List("4", "5", "6"), List("7", "8", "9")))
  }

}
