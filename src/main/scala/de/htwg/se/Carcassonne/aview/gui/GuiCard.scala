package de.htwg.se.Carcassonne.aview.gui

import java.io.File

import de.htwg.se.Carcassonne.controller.{Controller, PlayfieldChanged}
import javax.imageio.ImageIO
import java.awt.{Graphics2D, GridLayout}
import java.awt.image.BufferedImage

import de.htwg.se.Carcassonne.model.Grid

import scala.swing.event.MouseClicked
import scala.swing.{Dimension, GridBagPanel, GridPanel, Label}

class GuiCard(controller: Controller, row:Int, col:Int) extends GridPanel(1, 1) {

  preferredSize = new Dimension(80, 80)
  listenTo(controller)

  var img: BufferedImage = findImage()

  override def paint(g:Graphics2D):Unit = {
    g.drawImage(img, 0, 0, null)
  }

  def setCard(): Unit = {
    img = findImage()
    repaint()
  }

  def findImage(): BufferedImage = {
    val numToCharImage = List("D", "E", "G", "H", "I", "J", "K", "L", "N", "O", "R", "T", "U", "V", "W")

    val index = controller.playfield.grid.card(row, col).getID

    var dataImg: String = ""
    if (index == -1) {
      dataImg = "Empty"
    } else {
      dataImg = numToCharImage(index)
    }
    val src = "./src/main/scala/de/htwg/se/Carcassonne/aview/media/" + dataImg + ".png"
    ImageIO.read(new File(src))
  }

  def rotateCardR(): Unit = {

    repaint()
  }

  reactions += {
    case event: PlayfieldChanged => setCard()
  }


}
