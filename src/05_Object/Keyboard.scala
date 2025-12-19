import hevs.graphics.FunGraphics

import java.awt.event.{KeyAdapter, KeyEvent}

class Keyboard(val fg: FunGraphics) {

  def readKeyboard: Char = {
    val key: Char = ''
    fg.setKeyManager(new KeyAdapter() {
      if (e.getKeyChar == 'a') println("The key 'A' was pressed")
      if (e.getKeyCode == KeyEvent.VK_RIGHT) offset += 1
    }
  )


  }


}
