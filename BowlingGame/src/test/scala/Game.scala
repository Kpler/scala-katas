class Game {
  var totalScore = 0
  def score(): Int = 0

  def roll(i: Int) = {
    totalScore += i
  }
}
