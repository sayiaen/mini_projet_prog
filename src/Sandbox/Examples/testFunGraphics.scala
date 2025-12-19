package Sandbox.Examples

import hevs.graphics.FunGraphics
import java.awt.event.{KeyAdapter, KeyEvent}
import java.awt.Color

object JeuClavier extends App {
  val display = new FunGraphics(400, 400, "Contrôle Fluide")

  // Variables d'état pour les directions
  var directionHaut = false
  var directionBas = false
  var directionGauche = false
  var directionDroite = false
  var posX = 200
  var posY = 200

  // On écoute les deux événements : Pressé ET Relâché
  display.setKeyManager(new KeyAdapter() {
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_UP) directionHaut = true
      if (e.getKeyCode == KeyEvent.VK_DOWN) directionBas = true
      if(e.getKeyCode == KeyEvent.VK_LEFT) directionGauche = true
      if (e.getKeyCode == KeyEvent.VK_LEFT) directionGauche = true
      if (e.getKeyCode == KeyEvent.VK_RIGHT) directionDroite = true
    }

    override def keyReleased(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_UP) directionHaut = false
      if (e.getKeyCode == KeyEvent.VK_DOWN) directionBas = false
      if(e.getKeyCode == KeyEvent.VK_LEFT) directionGauche = false
      if(e.getKeyCode == KeyEvent.VK_RIGHT) directionDroite = false
    }
  })

  // Boucle de jeu
  while (true) {
    // Logique de mouvement basée sur l'état des touches
    if (directionHaut) posY -= 5
    if (directionBas)  posY += 5
    if(directionDroite) posX += 5
    if(directionGauche) posX -= 5

    //Controle des bords
    if(posY >= 360) posY = 360
    if(posY <= 0) posY = 0
    if(posX>= 360) posX = 360
    if(posX <= 0) posX = 0

    // Dessin
    display.frontBuffer.synchronized {
      display.clear()
      display.setColor(Color.BLUE)
      display.drawFillRect(posX, posY, 40, 40)
    }
    display.syncGameLogic(20) // 50 FPS
  }
}
