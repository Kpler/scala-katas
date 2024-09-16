class Game {
  var rolls: List[Int] = List()

  def roll(pins: Int): Unit = {
    if (rolls.length >= 20) {
      throw new IllegalArgumentException()
    }
    rolls = rolls :+ pins
    if (pins == 10 && rolls.length % 2 == 1) {
      rolls = rolls :+ 0
    }
  }

  private def isStrike(index: Int): Boolean = {
    if (index <= 2) {
      return false
    }

    val even = index % 2 == 0
    val shouldFirstRollBeDoubled = even && rolls(index-2) == 10
    val shouldSecondRollBeDoubled = rolls(index-3) == 10
    shouldFirstRollBeDoubled || shouldSecondRollBeDoubled
  }

  def score(): Int = {
    var res = 0
    for (i <- rolls.indices) {
      res += rolls(i)
      val even = i % 2 == 0
      if (isStrike(i)) {
        res += rolls(i)
      }
      if (even && i > 0 && rolls(i-1) + rolls(i-2) == 10) {
        res += rolls(i)
      }
    }
    res
  }
}
