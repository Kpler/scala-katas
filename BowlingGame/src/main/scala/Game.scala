class Game {
  private var scores: Seq[Int] = Seq()

  def score(): Int = {
    scores
      .grouped(2)
      .foldLeft((0, false)) {
        case ((acc, isSpare), Seq(x, y)) => {
          val isSpareNext = x + y == 10
          val actualXValue = if (isSpare) x + x else x
          (acc + actualXValue + y, isSpareNext)
        }
        case ((acc, isSpare), Seq(x)) => {
          val actualXValue = if (isSpare) x + x else x
          (acc + actualXValue, false)
        }
      }
      ._1

  }

  def roll(i: Int): Unit = {
    scores = scores :+ i
  }
}
