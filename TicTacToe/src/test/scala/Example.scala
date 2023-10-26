import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {
  

  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    game.score() should be ("love","love")
  }

}

class TennisGame() {
  def score() = ???

}