package de.htwg.se.Carcassonne.model.playfieldComponent.playfieldBaseImpl

import de.htwg.se.Carcassonne.model.gridComponent.CardInterface
import de.htwg.se.Carcassonne.model.gridComponent.gridBaseImpl.Card
import de.htwg.se.Carcassonne.model.playfieldComponent.CardManipulatorInterface

case class CardManipulator(playerCard: Int, card: CardInterface = new Card((0, 0))) extends CardManipulatorInterface {

  def this() = this(playerCard = -1)

  def getCard:CardInterface = card

  def rotateRight:CardManipulator = copy(card = card.rotateRight())

  def rotateLeft:CardManipulator = copy(card = card.rotateRight().rotateRight().rotateRight())

  def setPlayerToArea(index:Int):CardManipulator = {
    if(index < card.getAreas.size){
      val replacedPlayer = card.getAreas.apply(index).setPlayer(playerCard)
      val replacedCard = card.getAreas.updated(index, replacedPlayer)
      this.copy(card = Card(replacedCard, card.getID))
    } else {
      this
    }
  }

  def finalCard(x:Int, y:Int):CardInterface = this.card.setAreasXY(x, y)

}