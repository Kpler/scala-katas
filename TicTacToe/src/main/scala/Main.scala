object Main {
  def playerMove(matrix: Array[Int], position: Int, playerId: Int): Array[Int] = {
    matrix(position) = playerId
    return matrix
  }
  def main(args: Array[String]): Unit = {
    println("Hello World!")
  }
}
