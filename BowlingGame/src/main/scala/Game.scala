class Game {
  var currentScore: Int = 0
  var pinsHit: List[Int] = List()

  def roll(pins: Int): Unit = {
    if (pinsHit.length >= 20) {
      throw new IllegalArgumentException()
    }
    currentScore += pins
    pinsHit = pinsHit :+ pins
  }

  def score(): Int = {
    currentScore
  }
}
