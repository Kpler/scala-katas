trait Bonus {

  /** Define the bonus to apply on a first roll whose score (ie nb pins knocked down) is value */
  def bonusFirstRoll(value: Int): Int = value

  /** Define the bonus to apply on a second roll whose score (ie nb pins knocked down) is value */
  def bonusSecondRoll(value: Int): Int = value
}

/** NoBonus returns the value unchanged */
object NoBonus extends Bonus

/** Spare doubles the value of a first roll */
object Spare extends Bonus {

  override def bonusFirstRoll(value: Int): Int = value * 2
}

/** Strike doubles the value of both the first and second rolls */
object Strike extends Bonus {

  override def bonusFirstRoll(value: Int): Int = value * 2

  override def bonusSecondRoll(value: Int): Int = value * 2
}
