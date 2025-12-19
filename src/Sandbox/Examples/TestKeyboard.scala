package Sandbox.Examples

import hevs.graphics.FunGraphics

import java.awt.event.{KeyAdapter, KeyEvent}

object TestKeyboard extends App {

  val fg: FunGraphics = new FunGraphics(640, 480)
  var offset = 0

  // Gestionnaire de clavier
  fg.setKeyManager(new KeyAdapter() {
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyChar == 'a') println("The key 'A' was pressed")
      if (e.getKeyCode == KeyEvent.VK_RIGHT) offset += 1
    }
  })

  // Boucle principale
  while (true) {
    // 1. On efface l'écran (sur le buffer d'arrière-plan)
    fg.frontBuffer.synchronized {
      fg.clear()

      // 2. On dessine l'objet
      fg.drawRect(50 + offset * 2, 50 + offset * 2, 75, 75)
    }

    // 3. On synchronise pour maintenir 60 FPS
    fg.syncGameLogic(60)
  }
}