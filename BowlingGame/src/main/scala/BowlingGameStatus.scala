case class BowlingGameStatus(
  round: Int,
  totalScore: Int,
  bonus: Bonus,
  currentFrame: UnCompleteBowlingFrame
)

object BowlingGameStatus {
  def empty(): BowlingGameStatus = BowlingGameStatus(0, 0, NoBonus, EmptyFrame)
}
