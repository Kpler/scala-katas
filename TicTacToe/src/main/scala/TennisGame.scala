case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love")) {
  def isValid: Boolean = false


  val getScore = {
    (player1, player2) match {
      case (Player("40"), Player("40"))=> "deuce"
      case (Player("win"), Player("40")) | (Player("40"), Player("win")) => "win"
      case _ => s"${player1.score}-${player2.score}"
    }
  }

  def addOnePointToPlayer1(): TennisGame = TennisGame(player1.addPoint()._1, player2)




  def addOnePointToPlayer2(): TennisGame = TennisGame(player1, player2.addPoint()._1)
}

object TennisGame {
  val addOnePointToPlayer : TennisGame => TennisGame =  tg => tg.copy(tg.player1.addPoint()._1, tg.player2)
}


case class Player(score:String) {
  def addPoint(): (Player, Boolean) = score match {
    case "love" => (Player("15"), true)
    case "15" => (Player("30"), true)
    case "30" => (Player("40"), true)
    case "40" => (Player("win"), true)
    case "win"=> (Player("win"), false)
  }
}
