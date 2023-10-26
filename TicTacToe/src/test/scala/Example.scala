import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {
  

  "a starting game" should "have score set to love on both side" in {
    val game = new TennisGame()
    game.score() should be Score("love","love")
  }

}
class TennisGame() {
  def score(): (String, String) = ("love", "love")

}