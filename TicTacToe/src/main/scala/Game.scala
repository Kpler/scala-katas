case class Game(rows: List[List[String]] = List(List("1","2","3"), List("4","5","6"), List("7", "8", "9"))) {

  def getRowIndex(cell: Int): Int = cell / 3

  def getColIndex(cell: Int): Int = cell % 3

  def playXPlay(cell: Int ): Game  = play(cell, "X")

  def play0Play(cell: Int): Game = play(cell, "0")

  private def play(cell: Int, playerName: String) = {
    val rowIndex = getRowIndex(cell)
    val colIndex = getColIndex(cell)

    val row: List[String] = rows(rowIndex)
    row(cell) match {
      case "X" | "0" => throw new RuntimeException("Cell already occupied")
      case _ =>
    }
    val rowUpdated: List[String] = row.updated(colIndex, playerName)
    Game(rows.updated(rowIndex, rowUpdated))
  }

  val playerX: Player = Player("X")
  val playerO: Player = Player("O")


}
