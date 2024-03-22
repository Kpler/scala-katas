case class Game(rows: List[List[String]] = List(List("1","2","3"), List("4","5","6"), List("7", "8", "9"))) {

  def playXPlay(cell: Int ): Game  = {
    val firstRow: List[String] = rows.head

    val firstRowUpdated: List[String] =  firstRow.updated(cell, "X")
    rows.updated(0, firstRowUpdated.toList)
  }

  val cols: List[Int] = List(1,2,3)

  val playerX: Player = Player("X")
  val playerO: Player = Player("O")


    
}
