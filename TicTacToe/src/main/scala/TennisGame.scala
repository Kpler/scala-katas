case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love")) {
  def isValid: Boolean = getScore != "win"


  val getScore = {
    (player1, player2) match {
      case (Player("40"), Player("40"))=> "deuce"
      case (Player("win"), Player("40")) | (Player("40"), Player("win")) => "win"
      case _ => s"${player1.score}-${player2.score}"
    }
  }

  def addOnePointToPlayer1(): Either[String, TennisGame] = TennisGame(player1.addPoint().player, player2)


  def addOnePointToPlayer2(): Either[String, TennisGame] = TennisGame(player1, player2.addPoint().player)
}

object TennisGame {
  val addOnePointToPlayer : TennisGame => TennisGame =  tg => tg.copy(tg.player1.addPoint().player, tg.player2)
}


case class Player(score:String) {
  def addPoint(): ValidPlayer = score match {
    case "love" => ValidPlayer(Player("15"))
    case "15" => ValidPlayer(Player("30"))
    case "30" => ValidPlayer(Player("40"))
    case "40" => ValidPlayer(Player("win"))
    case "win"=> ValidPlayer(Player("win"), isValid = false)
  }
}

case class ValidPlayer(player: Player, isValid: Boolean = true)
