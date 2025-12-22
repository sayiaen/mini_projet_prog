import hevs.graphics.FunGraphics

import java.awt.event.{KeyAdapter, KeyEvent}

class Keyboard(val fg: FunGraphics) {

  var isDownPressed: Boolean = false
  var isLeftPressed: Boolean = false
  var isRightPressed: Boolean = false
  var isUpPressed: Boolean = false
  var isSpacePressed: Boolean = false

  fg.setKeyManager(new KeyAdapter() {
    override def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_A | KeyEvent.VK_LEFT => isLeftPressed = true
        case KeyEvent.VK_S | KeyEvent.VK_DOWN => isDownPressed = true
        case KeyEvent.VK_W | KeyEvent.VK_UP => isUpPressed = true
        case KeyEvent.VK_D | KeyEvent.VK_RIGHT=> isRightPressed = true
        case KeyEvent.VK_SPACE => isSpacePressed = true
        case _ => None
      }
    }

    override def keyReleased(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_A | KeyEvent.VK_LEFT => isLeftPressed = false
        case KeyEvent.VK_S | KeyEvent.VK_DOWN => isDownPressed = false
        case KeyEvent.VK_W | KeyEvent.VK_UP => isUpPressed = false
        case KeyEvent.VK_D | KeyEvent.VK_RIGHT=> isRightPressed = false
        case KeyEvent.VK_SPACE => isSpacePressed = false
        case _ => None
      }
    }
  })

}

