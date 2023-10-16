case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love")) {

  def getScore() = s"${player1.score}-${player2.score}"

  def addOnePointToPlayer1(): TennisGame = TennisGame(player1.addPoint(), player2)

  def addOnePointToPlayer2(): TennisGame = TennisGame(player1, player2.addPoint())

}

case class Player(score:String) {
  def addPoint(): Player = score match {
    case "love" => Player("15")
    case "15" => Player("30")
    case "30" => Player("40")
  }
}

