import scala.util.{ Try, Success }

sealed trait Player {
  def toggle: Player
}
case object Cross extends Player {
  override def toggle: Player = Circle
}
case object Circle extends Player {
  override def toggle: Player = Cross
}

class NotAllowed extends Exception

case class TicTacToe(
  a1: Option[Player] = None,
  a2: Option[Player] = None,
  a3: Option[Player] = None,
  b1: Option[Player] = None,
  b2: Option[Player] = None,
  b3: Option[Player] = None,
  c1: Option[Player] = None,
  c2: Option[Player] = None,
  c3: Option[Player] = None,
  nextPlayer: Player = Cross,
) {
  def play(position: String): Try[TicTacToe] = {
    val currentPlayer = this.nextPlayer
    val player = currentPlayer.toggle
    val res = position match {
      case "a1" => this.copy(a1 = Some(currentPlayer), nextPlayer = player)
      case "a2" => this.copy(a2 = Some(currentPlayer), nextPlayer = player)
      case "a3" => this.copy(a3 = Some(currentPlayer), nextPlayer = player)
      case "b1" => this.copy(b1 = Some(currentPlayer), nextPlayer = player)
      case "b2" => this.copy(b2 = Some(currentPlayer), nextPlayer = player)
      case "b3" => this.copy(b3 = Some(currentPlayer), nextPlayer = player)
      case "c1" if c1 == None => this.copy(c1 = Some(currentPlayer), nextPlayer = player)
      // case "c1" => Failure
      case "c2" => this.copy(c2 = Some(currentPlayer), nextPlayer = player)
      case "c3" => this.copy(c3 = Some(currentPlayer), nextPlayer = player)
      case _ => Failure
    }
    Success(res)
  }
}