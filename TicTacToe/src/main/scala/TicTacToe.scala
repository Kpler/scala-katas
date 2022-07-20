sealed trait Player 

case object Cross extends Player
case object Circle extends Player

case class TicTacToe(
   
  grid: List[List[Any]] = List(
      List(None, None, None),
      List(None, None, None),
      List(None, None, None)
   )
) {
   def play(
      player: Player,
      coordinates: List[Int]
   )={
      val updatedGrid = this.grid.updated(
         coordinates(0),
         this.grid(coordinates(0)).updated(coordinates(1), player)      
         )
      TicTacToe(updatedGrid)
   }


}
