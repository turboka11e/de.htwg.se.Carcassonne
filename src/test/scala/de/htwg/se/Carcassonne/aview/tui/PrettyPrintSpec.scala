package de.htwg.se.Carcassonne.aview.tui

import de.htwg.se.Carcassonne.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.Carcassonne.model.playerComponent.Player
import de.htwg.se.Carcassonne.model.playfieldComponent.playfieldBaseImpl.{Playfield, RawCardFactory}
import org.scalatest._

class PrettyPrintSpec extends WordSpec with Matchers {
  "PrettyPrint prints the Matrix and everything in the TUI" when {
    "is created it" should {
      val grid = new Grid(3)
      val pList = List(Player("Mark"))
      val p0 = new PrettyPrint(Playfield(grid = grid, freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, gameState = 0))
      val p1 = new PrettyPrint(Playfield(grid = grid, freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, gameState = 1))
      val p2 = new PrettyPrint(Playfield(grid = grid, freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, gameState = 2))
      val p3 = new PrettyPrint(Playfield(grid = grid, freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, gameState = 3))
      val p4 = new PrettyPrint(Playfield(grid = grid, freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, gameState = 4))
      val p5 = new PrettyPrint(Playfield(grid = grid, freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, gameState = 5, success = false))
      val ccp6 = new PrettyPrint(Playfield(grid = grid.place(0, 0, RawCardFactory("selectCard", 0).drawCard().setPlayerToArea(0).finalCard(0, 0)),
        freshCard = RawCardFactory("selectCard", 0).drawCard(), players = pList, success = false))
      "print playfieldView" in {
        val r = Console.BLUE + "r" + Console.RESET
        val c = Console.RED + "c" + Console.RESET
        val g = Console.YELLOW + "g" + Console.RESET
        val points = 0.0
        p0.playfieldView should be (
          "\n" + Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n" +
          s" ┌   ┐ ┌   ┐ ┌   ┐\t"+ Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n" +
          s"                  \n" +
          s" └   ┘ └   ┘ └   ┘\tDeine neue Karte:\n" +
          s" ┌   ┐ ┌   ┐ ┌   ┐\t ┌ $c ┐\n" +
          s"                  \t $r $r $r\n" +
          s" └   ┘ └   ┘ └   ┘\t └ $g ┘\n" +
          s" ┌   ┐ ┌   ┐ ┌   ┐\n" +
          s"                  \n" +
          s" └   ┘ └   ┘ └   ┘\n")
      }
      "print g1" in {
        p1.printo() should be("Name eingeben: ")
      }
      "print g2" in  {
        p2.printo() should be("Weitere Spieler hinzufügen? [y],[n]\nEingabe: ")
      }
      "print g3" in {
        p3.printo() should be(p3.playfieldView + "Karte drehen: Rechts [r], Links [l]\nFertig: [y]\nEingabe: ")
      }
      "print g4" in {
        p4.printo() should be(p4.playfieldView + "Männchen auf Gebiet setzen! Blau [0], Rot [1], Gelb [2], Grün [3]\nEingabe: ")
      }
      "print g5" in {
        p5.printo() should be(p5.playfieldView + p5.illegalPlace + "Setze Karte in das Spielfeld! [x y]\nEingabe: ")
      }
      "print the player " in {
        val points = 0.0
        p0.playerLine should be(Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n")
        p1.playerLine should be(Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n")
        p2.playerLine should be(Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n")
        p3.playerLine should be(Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n")
        p4.playerLine should be(Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n")
        p5.playerLine should be(Console.BLUE + f"Mark ($points%.2f)" + Console.RESET + "    \n")
      }
      "print exception" in {
        p0.illegalPlace should be("")
        p1.illegalPlace should be("")
        p2.illegalPlace should be("")
        p3.illegalPlace should be("")
        p4.illegalPlace should be("")
        p5.illegalPlace should be("Keine gültige Platzierung! ")
      }
      "print the nameLine" in {
        p0.nameLine should be(" ┌   ┐ ┌   ┐ ┌   ┐\t" + Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n")
        p1.nameLine should be(" ┌   ┐ ┌   ┐ ┌   ┐\t" + Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n")
        p2.nameLine should be(" ┌   ┐ ┌   ┐ ┌   ┐\t" + Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n")
        p3.nameLine should be(" ┌   ┐ ┌   ┐ ┌   ┐\t" + Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n")
        p4.nameLine should be(" ┌   ┐ ┌   ┐ ┌   ┐\t" + Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n")
        p5.nameLine should be(" ┌   ┐ ┌   ┐ ┌   ┐\t" + Console.BLUE + "Mark" + Console.RESET + " ist an der Reihe\n")
      }
      "print the betweenline" in {
        p0.betweenLine should be("                  \n")
      }
      "print the freshCardPart" in {
        val r = Console.BLUE + "r" + Console.RESET
        val c = Console.RED + "c" + Console.RESET
        val g = Console.YELLOW + "g" + Console.RESET
        p0.freshCardPart should be (" └   ┘ └   ┘ └   ┘\tDeine neue Karte:\n" +
          s" ┌   ┐ ┌   ┐ ┌   ┐\t ┌ $c ┐\n" +
          s"                  \t $r $r $r\n" +
          s" └   ┘ └   ┘ └   ┘\t └ $g ┘\n")
      }
      "print the Rest" in {
        p0.restPart should be (" ┌   ┐ ┌   ┐ ┌   ┐\n                  \n └   ┘ └   ┘ └   ┘\n")
      }
      "print the topRow" in {
        p0.topRow(0) should be (" ┌   ┐ ┌   ┐ ┌   ┐")
        p1.topRow(0) should be (" ┌   ┐ ┌   ┐ ┌   ┐")
        p2.topRow(0) should be (" ┌   ┐ ┌   ┐ ┌   ┐")
        p3.topRow(0) should be (" ┌   ┐ ┌   ┐ ┌   ┐")
        p4.topRow(0) should be (" ┌   ┐ ┌   ┐ ┌   ┐")
        p5.topRow(0) should be (" ┌   ┐ ┌   ┐ ┌   ┐")
      }
      "print the midRow" in {
        p0.midRow(0) should be ("                  ")
        p1.midRow(0) should be ("                  ")
        p2.midRow(0) should be ("                  ")
        p5.midRow(0) should be ("                  ")
        p5.midRow(0) should be ("                  ")
        p5.midRow(0) should be ("                  ")
      }
      "print the lowRow" in {
        p0.lowRow(0) should be (" └   ┘ └   ┘ └   ┘")
        p1.lowRow(0) should be (" └   ┘ └   ┘ └   ┘")
        p2.lowRow(0) should be (" └   ┘ └   ┘ └   ┘")
        p3.lowRow(0) should be (" └   ┘ └   ┘ └   ┘")
        p4.lowRow(0) should be (" └   ┘ └   ┘ └   ┘")
        p5.lowRow(0) should be (" └   ┘ └   ┘ └   ┘")
      }
      "print the topSeg" in {
        p0.topSeg(0,0) should be (" ┌   ┐")
        p1.topSeg(0,0) should be (" ┌   ┐")
        p2.topSeg(0,0) should be (" ┌   ┐")
        p3.topSeg(0,0) should be (" ┌   ┐")
        p4.topSeg(0,0) should be (" ┌   ┐")
        p5.topSeg(0,0) should be (" ┌   ┐")
      }
      "print the midSeg" in {
        p0.midSeg(0,0) should be ("      ")
        p1.midSeg(0,0) should be ("      ")
        p2.midSeg(0,0) should be ("      ")
        p3.midSeg(0,0) should be ("      ")
        p4.midSeg(0,0) should be ("      ")
        p5.midSeg(0,0) should be ("      ")
      }
      "print the lowSeg" in {
        p0.lowSeg(0,0) should be (" └   ┘")
        p1.lowSeg(0,0) should be (" └   ┘")
        p2.lowSeg(0,0) should be (" └   ┘")
        p3.lowSeg(0,0) should be (" └   ┘")
        p4.lowSeg(0,0) should be (" └   ┘")
        p5.lowSeg(0,0) should be (" └   ┘")
      }
      "get the colored corner" in {
        p0.getColoredCorner(0,0, 'n') should be (" ")
        p1.getColoredCorner(0,0, 'w') should be (" ")
        p2.getColoredCorner(0,0, 'n') should be (" ")
        p3.getColoredCorner(0,0, 's') should be (" ")
        p4.getColoredCorner(0,0, 'n') should be (" ")
        p5.getColoredCorner(0,0, 'n') should be (" ")
        val r = Console.BLUE + "r" + Console.RESET
        ccp6.getColoredCorner(0, 0, 'w') should be(r)
      }
    }
  }
}
