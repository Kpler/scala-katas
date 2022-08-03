class Game {
  var rolls: List[Int] = List()

  def roll(pins: Int): Unit = {
    if (rolls.length >= 20) {
      throw new IllegalArgumentException()
    }
    rolls = rolls :+ pins
  }

  def score(): Int = {
    var res = 0
    for (i <- rolls.indices) {
      res += rolls(i)
      val even = i % 2 == 0
      if (even && i > 0 && rolls(i-1) + rolls(i-2) == 10) {
        res += rolls(i)
      }
    }
    res
  }
}
