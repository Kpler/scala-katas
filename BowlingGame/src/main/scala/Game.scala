class Game {
  var scores: Array[Int] = Array()

  def score(): Int = {
    scores.grouped(2).foldLeft(0)((acc, y) => acc + sum(y))
  }

  def roll(i: Int) = {
    scores + i
  }
}
