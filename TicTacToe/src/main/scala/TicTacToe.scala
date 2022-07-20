case class TicTacToe(
   
  grid: List[List[Any]] = List(
      List(None, None, None),
      List(None, None, None),
      List(None, None, None)
   )
) {
   def play(
      playerId: Int,
      coordinates: List[Int]
   )={

      // fix test
      return this.grid.updated(
         coordinates(0),
         this.grid(coordinates(1), playerId)      
         )

   }


}
