package de.htwg.se.Carcassonne.util

class UndoManager {
  private var undoStack: List[Command]= List.empty
  private var redoStack: List[Command]= List.empty

  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep()
  }

  def undoStep(): Unit = {
    undoStack match {
      case  Nil =>
      case head::stack =>
        head.undoStep()
        undoStack=stack
        redoStack= head::redoStack
    }
  }

  def redoStep(): Unit = {
    redoStack match {
      case Nil =>
      case head::stack =>
        head.redoStep()
        redoStack=stack
        undoStack=head::undoStack
    }
  }
}
