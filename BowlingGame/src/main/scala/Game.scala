class Game {
  private val maxPins = 10
  private var scores: Seq[Int] = Seq()

  def score(): Int = {
    scores
      .grouped(2)
      .foldLeft((0, false)) {
        case ((acc, isSpare), Seq(firstRoll, secondRoll)) => {
          val isSpareNext = firstRoll + secondRoll == maxPins
          val actualXValue = if (isSpare) firstRoll + firstRoll else firstRoll
          (acc + actualXValue + secondRoll, isSpareNext)
        }
        case ((acc, isSpare), Seq(uniqueRoll)) => {
          val actualXValue = if (isSpare) uniqueRoll + uniqueRoll else uniqueRoll
          (acc + actualXValue, false)
        }
      }
      ._1

  }

  def roll(i: Int): Unit = {
    if (i == maxPins)
      scores = scores :++ Seq(i, 0)
    else
      scores = scores :+ i
  }
}
