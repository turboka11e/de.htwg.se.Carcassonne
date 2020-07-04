package de.htwg.se.Carcassonne.model.gridComponent

trait GridInterface {

  def getCount:Int
  def getSize:Int
  def card(row: Int, col: Int): CardInterface
  def set(row: Int, col: Int, newCard:CardInterface): GridInterface
  def placeable(newCard:CardInterface):Boolean
  def place(row: Int, col: Int, newCard:CardInterface): GridInterface
  def getTerritories:List[List[(Int, AreaInterface)]]
}

trait MatrixInterface[T] {

  def getCount:Int
  def card(row:Int, col:Int):CardInterface
  def checkEdge(row: Int, col: Int, dir: Char):Boolean
  def checkEnvEmpty(row: Int, col: Int, dir:Char):Boolean
  def checkEnv(row: Int, col: Int, dir: Char, checkCard: CardInterface):Boolean
  def getDirEnv(row: Int, col: Int, dir:Char):Option[AreaInterface]
  def hasNeighbor(row: Int, col: Int): Boolean
  def checkSet(row: Int, col: Int, checkCard:CardInterface): Boolean
}

trait CardInterface {

  def isEmpty: Boolean
  def getID: (Int, Int)
  def setAreasXY(x:Int, y:Int):CardInterface
  def getValue(dir:Char): Char
  def getPlayer(dir:Char): Int
  def getAreas:List[AreaInterface]
  def getCornersLookingFrom(dir:Char): List[Char]
  def getAreaLookingFrom(dir:Char): AreaInterface
  def rotateRight():CardInterface
  def toString:String
  def topString:String
  def midString:String
  def lowString:String
}

trait AreaInterface {

  def isEmpty:Boolean
  def getValue:Char
  def getCorners:List[Char]
  def getPlayer:Int
  def setPlayer(p:Int):AreaInterface
  def getCoord:(Int, Int)
  def setCoord(a:Int, b:Int):AreaInterface
  def rotateRight():AreaInterface
}