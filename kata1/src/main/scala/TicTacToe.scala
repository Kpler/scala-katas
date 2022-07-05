case class TicTacToe() {
  def getAt(position: String): Option[Player] = Some(Circle)

  def play(position: String): TicTacToe = this
}

sealed trait Player
case object Cross extends Player
case object Circle extends Player
