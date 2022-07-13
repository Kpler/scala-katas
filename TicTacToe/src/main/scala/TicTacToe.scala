
case class TicTacToe(val grid: Set[String] = Set()) {
  def play(position: String): TicTacToe = {
    TicTacToe(grid ++ Set( position))
  }
}