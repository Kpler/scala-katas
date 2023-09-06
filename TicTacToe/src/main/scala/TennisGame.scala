case class TennisGame(
                       player1: Player = Player("love"),
                       player2: Player = Player("love"),
                       score: String = "love-love") {

  def addOnePointToPlayer1(): TennisGame = TennisGame("15-love")

  def addOnePointToPlayer2(): TennisGame = TennisGame("love-15")

}

case class Player(score:String)

