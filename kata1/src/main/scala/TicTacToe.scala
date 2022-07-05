case class TicTacToe(grid: List[String]) {
  def getAt(position: String): Option[Player] = Some(Cross)

  def play(position: String): TicTacToe = copy(grid=position +: grid)
}

sealed trait Player
case object Cross extends Player
case object Circle extends Player
