import hevs.graphics.FunGraphics

import java.awt.event.{KeyAdapter, KeyEvent}

class SnakeKeyboard(val fg: FunGraphics) {

  // Gestionnaire de clavier
  def getKeyPressed(): Char = {
    var key: Char = ' '
    fg.setKeyManager(new KeyAdapter() {
      override def keyPressed(e: KeyEvent): Unit = {
        e.getKeyCode match {
          case KeyEvent.VK_A | KeyEvent.VK_LEFT => key = 'a'
          case KeyEvent.VK_S | KeyEvent.VK_DOWN => key = 's'
          case KeyEvent.VK_W | KeyEvent.VK_UP => key = 'w'
          case KeyEvent.VK_D | KeyEvent.VK_RIGHT=> key = 'd'
        }
      }
  })
  key
  }

  def getKeyRealeased(): Char = {
    var key: Char = ' '
    fg.setKeyManager(new KeyAdapter() {
      override def keyReleased(e: KeyEvent): Unit = {
        e.getKeyCode match {
          case KeyEvent.VK_A | KeyEvent.VK_LEFT => key = 'a'
          case KeyEvent.VK_S | KeyEvent.VK_DOWN => key = 's'
          case KeyEvent.VK_W | KeyEvent.VK_UP => key = 'w'
          case KeyEvent.VK_D | KeyEvent.VK_RIGHT => key = 'd'
        }
      }
    })
    key
  }
}

