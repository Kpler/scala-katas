case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love")) {

  val getScore = {
    (player1, player2) match {
      case (Player("40"), Player("40"))=> "deuce"
      case (Player("win"), Player("40")) | (Player("40"), Player("win")) => "win"
      case _ => s"${player1.score}-${player2.score}"
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
