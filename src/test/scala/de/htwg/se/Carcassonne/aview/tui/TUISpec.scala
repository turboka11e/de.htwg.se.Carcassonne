package de.htwg.se.Carcassonne.aview.tui

import de.htwg.se.Carcassonne.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.Carcassonne.model.playfieldComponent.playfieldBaseImpl.Playfield
import org.scalatest._

class TUISpec extends WordSpec with Matchers {

  "A Carcasonne TUI" should  {
    val controller = new Controller(Playfield())
    val playfield = Playfield().fieldSize(3)

    val tui = new TUI(controller)
    "create a new game on input 'new'" in {
      tui.processInputLine("new")
      controller.playfield should be(Playfield())
    }
    "create Gamefield of size 3 on input 3" in {
      tui.forkDigit(3)
      controller.playfield should be(playfield)
    }
    "decide on the gamestate when input 'y'" in {
      tui.processInputLine("y")
      controller.playfield should be(playfield)
    }
    "decide on the gamestate when input 'n" in {
      tui.processInputLine("n")
      controller.playfield should be(playfield)
    }
    "rotates the card right when input 'r'" in {
      tui.processInputLine("r")
      controller.playfield should be(playfield.rotateR)
    }
    "rotates the card left when input 'l'" in {
      tui.processInputLine("l")
      controller.playfield should be (controller.playfield)
    }
    "validates the input '_'" in {
      tui.processInputLine("_")
      tui.validateLongString("")
    }
    "decideds at Gamestate 2" in {
      controller.changeGameState(2)
      tui.processInputLine("y")
      controller.playfield.gameState should be(1)
      tui.processInputLine("n")
      controller.playfield.gameState should be(1)
      controller.changeGameState(3)
      tui.processInputLine("y")
      controller.playfield.gameState should be(4)
      controller.changeGameState(4)
      tui.processInputLine("n")
      controller.playfield.gameState should be(5)
      controller.changeGameState(1)
    }
    "validate long String" in {
      tui.processInputLine("0 1")
      controller.changeGameState(3)
      tui.processInputLine("0")
      controller.playfield.changeGameState(4)
      tui.processInputLine("0")
      controller.playfield.gameState should be(3)
    }

  }

}
