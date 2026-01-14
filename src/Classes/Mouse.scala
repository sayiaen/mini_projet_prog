//Cette classe permet de gérer la souris


import hevs.graphics.FunGraphics
import java.awt.event.{MouseAdapter, MouseEvent}

class Mouse(val fg: FunGraphics) {


  var hasClicked: Boolean = false //Boolean qui permet de savoir si la souris a clique
  var posy = 0
  var posx = 0

  // This will handle the mouse
  fg.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e

      // Get the mouse position from the eventval posx = event.getX
      posy = event.getY
      posx = event.getX

      println(s"Mouse position $posx - $posy")

      hasClicked = true
    }


  })
}