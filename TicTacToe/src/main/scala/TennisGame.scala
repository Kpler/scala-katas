case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love")) {

  val getScore ={
    s"${player1.score}-${player2.score}" match {
      case "40-40"=> "deuce"
      case "win-40"=> "win"
      case other => other
    }
  }

  def addOnePointToPlayer1(): TennisGame = TennisGame(player1.addPoint(), player2)

  def addOnePointToPlayer2(): TennisGame = TennisGame(player1, player2.addPoint())

}

case class Player(score:String) {
  def addPoint(): Player = score match {
    case "love" => Player("15")
    case "15" => Player("30")
    case "30" => Player("40")
    case "40" => Player("win")
  }
}
