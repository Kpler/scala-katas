class Game {

  private var totalScore = 0
  def score(): Int = totalScore

  def roll(i: Int) = {
    totalScore += i
  }
}
