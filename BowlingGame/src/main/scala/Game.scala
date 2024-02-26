class Game {
  var scores: Array[Int] = Array()

  def score(): Int = {
    scores.grouped(2).foldLeft((0, false)) { case ((acc, isSpare), Array(x, y)) => {
      val isSpareNext = (x + y == 10)
      val actualXValue = if (isSpare) x + x else x
      (acc + actualXValue + y, isSpareNext)
    }
    }._1

  }

  def roll(i: Int): Unit = {
    scores + i
    Unit
  }
}
