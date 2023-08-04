class BowlingGame() {
  private var rolledPins = List[Int]()

  private def isSpare(idx: Int): Boolean =
    idx % 2 == 0 && idx > 0  && (rolledPins(idx - 2) + rolledPins(idx - 1) == 10)

  private def isStrike(idx: Int): Boolean =
    idx % 2 != 0 && idx >= 3 && (rolledPins(idx - 3) == 10 && rolledPins(idx - 2) == 0)

  def score(): Int = {
    var totalScore = 0

    rolledPins.zipWithIndex.foreach((zippedPins) => {
      val (currPins, idx) = zippedPins
      if (isStrike(idx)) {
        totalScore += rolledPins(idx - 1) + currPins
      } else if (isSpare(idx) && rolledPins(idx - 2) != 10) { // excluding idx-2 because this is a strike case
        totalScore += currPins
      }
      
      totalScore += currPins
    })
    totalScore
  }

  def roll(pins: Int): Unit = {
    if (this.rolledPins.length < 22) {
        this.rolledPins = this.rolledPins :+ pins
        if (pins == 10) {
        this.rolledPins = this.rolledPins :+ 0
        }
    } else {
        // execute end score
        score()
    }
  }

}