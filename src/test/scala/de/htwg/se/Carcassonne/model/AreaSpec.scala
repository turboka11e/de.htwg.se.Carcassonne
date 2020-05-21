package de.htwg.se.Carcassonne.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AreaSpec extends WordSpec with Matchers {

  "An Area" when {
    "not set to any value " should {
      val emptyArea = Area()
      "have List with ' '" in {
      }
    }
    "filld with corners" should {
      val realArea = Area('c', List('n', 's', 'w'))
      " check if there are corners to return then rotate right" in {
        realArea.getCorners should be (List('n', 's', 'w'))
        realArea.rotateRight() should be(Area('c', List('e', 'w', 'n')))
      }
    }
  }

}