class Game {
  private val maxPins = 10
  private var gameStatus = BowlingGameStatus.empty()

  def score(): Int = gameStatus.totalScore

  def roll(i: Int): Unit = {
    gameStatus = rollFunc(i, gameStatus)
  }

  private def rollFunc(i: Int, status: BowlingGameStatus): BowlingGameStatus = {

    status.currentFrame match {
      case EmptyFrame if i == maxPins =>
        BowlingGameStatus(
          status.round + 1,
          status.totalScore + status.bonus.bonusFirstRoll(i),
          Strike,
          EmptyFrame
        )
      case EmptyFrame =>
        BowlingGameStatus(
          status.round,
          status.totalScore + status.bonus.bonusFirstRoll(i),
          status.bonus,
          FirsRollFrame(i)
        )
      case FirsRollFrame(firstRoll) =>
        BowlingGameStatus(
          status.round + 1,
          status.totalScore + status.bonus.bonusSecondRoll(i),
          if (firstRoll + i == maxPins) Spare else NoBonus,
          EmptyFrame
        )
    }
  }
}
