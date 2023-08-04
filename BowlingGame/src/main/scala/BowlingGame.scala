class BowlingGame() {
  private var rolledPins = List[Int]()
  
  private def isSpare(idx: Int): Boolean =
    idx % 2 == 0 && idx > 0 && (rolledPins(idx - 2) + rolledPins(idx - 1) == 10)

  private def isStrike(idx: Int): Boolean =
    idx % 2 == 0 && (rolledPins(idx - 2) == 10 && rolledPins(idx - 1) == 0)

  def score() : Int = {
    var totalScore = 0

    rolledPins.zipWithIndex.foreach((zippedPins) => {
      val (currPins, idx) = zippedPins
      if (isSpare(idx)) {
        totalScore += currPins
      }
      totalScore += currPins
    })
    totalScore
  }

  def roll(pins: Int) : Unit = {

    this.rolledPins = this.rolledPins :+ pins

    if (pins == 10) {
      this.rolledPins = this.rolledPins :+ 0
    }

  }

}