object Main {
  def playerMove(matrix: List[Int], position: Int, playerId: Int): List[Int] = {
    return matrix.updated(position, playerId)
  }
  def main(args: Array[String]): Unit = {
    println("Hello World!")
  }
}
