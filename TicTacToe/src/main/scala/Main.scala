object Main {
  def playerMove(matrix: Array[int], position: int, playerId: int): Array[int] = {
    matrix.get(position) = playerId
    return matrix
  }
  def main(args: Array[String]): Unit = {
    println("Hello World!")
  }
}
