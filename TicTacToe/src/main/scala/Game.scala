class Game {
  def playXPlay(cell: Int ): Seq[List[String]] = {
    val firstRow: List[String] = rows(0)
    firstRow match {
      case head :: tail =>  List("X" :: tail)
    }
  }

  val cols: List[Int] = List(1,2,3)
  val rows: List[List[String]] = List(List("1","2","3"), List("4","5","6"), List("7", "8", "9"))
  val playerX: Player = Player("X")
  val playerO: Player = Player("O")


    
}
