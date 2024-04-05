case class Game(rows: List[List[String]] = List(List("1","2","3"), List("4","5","6"), List("7", "8", "9"))) {

  def getRowIndex(cell: Int): Int = cell / 3

  def getColIndex(cell: Int): Int = cell % 3

  def playXPlay(cell: Int ): Game  = play(cell, "X")

  def play0Play(cell: Int): Game = play(cell, "0")

  private def play(cell: Int, playerName: String) = {
    val rowIndex = getRowIndex(cell)
    val colIndex = getColIndex(cell)

    val row: List[String] = rows(rowIndex)
    row(colIndex) match {
      case "X" | "0" => throw new CellOccupiedException()
      case _ =>
    }
    val rowUpdated: List[String] = row.updated(colIndex, playerName)
    Game.fromBoard(rows.updated(rowIndex, rowUpdated))
  }

  val playerX: Player = Player("X")
  val playerO: Player = Player("O")
}

object Game{
  def fromBoard(rows: List[List[String]]): Game ={

    rows.foreach(row => {
      row.foreach(col => {
        if (col != "X" && col != "O")
          return new Game(rows)
      })
    })
    throw new GameOverException()
  }
}