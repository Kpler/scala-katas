sealed trait Player {
  def toggle: Player = this match {
    case Circle => Cross
    case Cross => Circle
  }
}

case object Circle extends Player
case object Cross extends Player

sealed trait Case

case object A1 extends Case
case object B1 extends Case
case object C1 extends Case
case object A2 extends Case
case object B2 extends Case
case object C2 extends Case
case object A3 extends Case
case object B3 extends Case
case object C3 extends Case

case class Game(grid: Map[Case,Player] = Map[Case,Player](),
                nextPlayer: Player = Circle) {

  def play(_case: Case): Game = {
    if (grid.contains(_case)) this 
    else Game(grid + (_case -> nextPlayer), nextPlayer.toggle)
  }

  def winner: Option[Player] = {
    if (
      grid.get(A1) == Circle && grid.get(A2) == Circle && grid.get(A3) == Circle
    )
    Some(Circle)
    else None
  }
}
