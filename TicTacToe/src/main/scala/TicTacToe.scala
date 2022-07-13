import TicTacToe.AUTHORIZED_POSITIONS

object TicTacToe {
  private val AUTHORIZED_POSITIONS = Set("a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3")
}
case class TicTacToe(val grid: Set[String] = Set()) {

  def play(position: String): Either[String, TicTacToe] = {
    if (grid.contains(position))
      Left("Already contained position")
    else if (!AUTHORIZED_POSITIONS.contains(position))
      Left("Invalid position")
    else
      Right(TicTacToe(grid ++ Set(position)))
  }
}