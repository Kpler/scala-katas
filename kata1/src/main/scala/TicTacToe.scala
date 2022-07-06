sealed trait Player
case object Cross extends Player
case object Circle extends Player

sealed trait Position
case object A1 extends Position

case class TicTacToe(

  a1: Option[Player] = None,
  a2: Option[Player] = None,
  a3: Option[Player] = None,
  b1: Option[Player] = None,
  b2: Option[Player] = None,
  b3: Option[Player] = None,
  c1: Option[Player] = None,
  c2: Option[Player] = None,
  c3: Option[Player] = None
) {
  def play(position: String): TicTacToe = position match {
    case "a1" => this.copy(a1=Some(Cross))
    case "a2" => this.copy(a2=Some(Cross))
    case "a3" => this.copy(a3=Some(Cross))
    case "b1" => this.copy(b1=Some(Cross))
    case "b2" => this.copy(b2=Some(Cross))
    case "b3" => this.copy(b3=Some(Cross))
    case "c1" => this.copy(c1=Some(Circle))
    case "c2" => this.copy(c2=Some(Cross))
    case "c3" => this.copy(c3=Some(Cross))
  }
}