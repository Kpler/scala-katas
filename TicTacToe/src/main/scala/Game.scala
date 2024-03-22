case class Game(rows: List[List[String]] = List(List("1","2","3"), List("4","5","6"), List("7", "8", "9"))) {

  def playXPlay(cell: Int ): Game  = play(cell, "X")

  def play0Play(cell: Int): Game = play(cell, "0")

  private def play(cell: Int, playerName: String) = {
    val firstRow: List[String] = rows.head
    val firstRowUpdated: List[String] = firstRow.updated(cell, playerName)
    Game(rows.updated(0, firstRowUpdated))
  }

  val playerX: Player = Player("X")
  val playerO: Player = Player("O")

}
