package de.htwg.se.Carcassonne.controller.controllerComponent

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, PLAYER, RESIZE, FIRSTCARD, NEW, UNDO, REDO, MANICAN, GAMEOVER, ROTATE, PLACE, SAVE, LOADED = Value

  val map: Map[GameStatus, String] = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "A new game was created",
    RESIZE -> "Game was resized",
    PLAYER -> "Player added",
    FIRSTCARD -> "First Card drawn",
    ROTATE -> "Card was rotated",
    MANICAN -> "Manican was put",
    PLACE -> "A Card was placed",
    UNDO -> "Undone one step",
    REDO -> "Redone one step",
    GAMEOVER -> "Game Over",
    SAVE -> "Game saved",
    LOADED -> "Game loaded")

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}