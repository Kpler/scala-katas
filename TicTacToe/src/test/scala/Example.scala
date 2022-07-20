import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "A List" should "drop the first values" in {
    val stack = List(3, 2)
    stack.drop(1) should be (List(2))
  }

  "A New game" should " return a new object" in {
    val game: Game = Game()
  }

  "A New game" should "have grid field of type dict" in {
    val game: Game = Game()
    game.grid should be (Map[Case,Player]())
  }

  "A player" should "should play a valid case" in {
    val game: Game = Game()
    val game1 = game.play(A1)
    game1.grid should be (Map(A1 -> Circle))
  }

  "Player" should "should play 2 times" in {
    val game: Game = Game()
    val game1 = game.play(A1).play(A2)
    game1.grid should be (Map(A1 -> Circle, A2 -> Cross))
  }

  "Players" should "should play 2 times on the same case" in {
    val game: Game = Game()
    val game1 = game.play(A1).play(A1)
    game1.grid should be (Map(A1 -> Circle))
  }

  "Game" should "win" in {
    val game: Game = Game()
    val games = game.play(A1).play(B1).play(A2).play(B2).play(A3)
    games.winner should be (Some(Circle))
  }

    "Game" should "not win" in {
    val game: Game = Game()
    game.winner should be (None)
  }
}
