class Game {
  var currentScore: Int = 0
  var pinsHit: List[Int] = List()

  def roll(pins: Int): Unit = {
    if (pinsHit.length >= 20) {
      throw new IllegalArgumentException()
    }
    pinsHit = pinsHit :+ pins
  }

  def score(): Int = {
    var res = 0

    for (i <- 0 until pinsHit.length) {
      val even = i % 2 == 0
      res += pinsHit(i)
    }

    res
  }
}
