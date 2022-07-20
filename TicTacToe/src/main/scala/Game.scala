sealed trait Player

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

class Game {
  val grid = Map[Case,Player]()
  
  def play(_case: Case): Game = {
    this
  }

}
