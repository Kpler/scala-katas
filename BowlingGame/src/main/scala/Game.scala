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

  def score(): Int = rolls
    .zipWithIndex
    .foldLeft(0) { (acc, item) =>
      val (pins, index) = item
      (isStrike(index), isSpare(index)) match {
        case (true, _) | (_, true) => (pins * 2) + acc
        case (_, _) => pins + acc
      }
    }

  private def isSpare(index: Int) = {
    val even = index % 2 == 0
    even && index > 0 && rolls(index - 1) + rolls(index - 2) == 10
  }

  private def isStrike(index: Int): Boolean = {
    if (index <= 2) {
      return false
    }
    val even = index % 2 == 0
    val shouldFirstRollBeDoubled = even && rolls(index - 2) == 10
    val shouldSecondRollBeDoubled = rolls(index - 3) == 10
    shouldFirstRollBeDoubled || shouldSecondRollBeDoubled
  }
}
