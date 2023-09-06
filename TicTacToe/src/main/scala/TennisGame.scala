case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love")) {

  def getScore() = s"${player1.score}-${player2.score}"

  def addOnePointToPlayer1(): TennisGame = TennisGame(Player("15"), player2)

  def addOnePointToPlayer2(): TennisGame = TennisGame(player1, Player("15"))

}

case class Player(score:String)

