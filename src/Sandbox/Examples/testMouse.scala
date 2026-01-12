import hevs.graphics.FunGraphics
import java.awt.event.{MouseAdapter, MouseEvent}

object TestMouse extends App {
  val fg = new FunGraphics(300, 300)

  // This will handle the mouse
  fg.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e

      // Get the mouse position from the eventval posx = event.getX
      val posy = event.getY
      val posx = event.getX
      println(s"Mouse position $posx - $posy")

      // Draws a circle where the mouse was during click
      fg.drawFilledCircle(posx, posy, 5)
    }
  })
}