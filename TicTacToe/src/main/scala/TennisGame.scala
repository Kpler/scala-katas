case class TennisGame(score: String = "love-love") {
  def getScore() : String = score

  def addOnePointToPlayer1() : TennisGame = new TennisGame("15-love")


}