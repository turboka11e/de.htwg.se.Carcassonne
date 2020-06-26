package de.htwg.se.Carcassonne.model

case class Playfield(players:List[Player] = Nil, isOn: Int = 0, grid: Grid = new Grid(1),
                     freshCard:CardManipulator = new CardManipulator(), gameState:Int =  0, success:Boolean = true) {

  def changeGameState(gs:Int):Playfield = copy(gameState = gs)

  def getGameState:Int = gameState

  def getSuccess:Boolean = success

  def fieldSize(size:Int):Playfield = copy(grid = new Grid(size), gameState = 1)                    // GameState 0

  def addPlayer(name:String):Playfield = copy(players = players ::: List(Player(name)), gameState = 2)       // GameState 1

  def getFreshCard:Playfield = copy(freshCard = RawCardFactory("randomCard", isOn).drawCard())            // GameState 2

  def getFreshCard(select:Int):Playfield = copy(freshCard = RawCardFactory.apply("selectCard", isOn, select).drawCard()) // for Testing

  def rotateR:Playfield = copy(freshCard = freshCard.rotateRight)                                   // Gamestate 3

  def rotateL:Playfield = copy(freshCard = freshCard.rotateLeft)                                    // Gamestate 3

  def selectArea(nr:Int):Playfield = {
    if(!freshCard.card.areas.exists(p => p.getPlayer != -1) && freshCard.card.areas.apply(nr).getValue != 'g') {
      copy(freshCard = freshCard.setPlayerToArea(nr), gameState = 5)
    } else {
      this
    }
  }                // Gamestate 4

  def placeCard(x: Int, y: Int): Playfield = { // Gamestate 5
    val check = grid.getCount
    val CardAdded = grid.place(x, y, freshCard.finalCard(x, y))
    if (check == CardAdded.getCount){
      copy(success = false)
    }else{
      copy(grid = CardAdded, success = true, gameState = 3, players = Points(CardAdded.getTerritories, players).updatePoints())
    }
  }

  def nextPlayer: Playfield = {
    if(isOn == players.size - 1){
      copy(isOn = 0)
    } else {
      copy(isOn = isOn + 1)
    }
  }

  def playFieldToString:String = {
    new PrettyPrint(grid, freshCard, players, isOn, success).printo(gameState)
  }
}
