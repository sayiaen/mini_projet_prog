import hevs.graphics.FunGraphics
import java.awt.Color

class SnakeDisplay {
  //Constantes
  private val HEIGHT = 800
  private val WIDTH = 800
  val display = new FunGraphics(WIDTH, HEIGHT)
  val snakeKeyboard = new SnakeKeyboard(display)
  
  //Variables
  // Variables d'état pour les directions
  var directionHaut = false
  var directionBas = false
  var directionGauche = false
  var directionDroite = false
  var posX = 200
  var posY = 200

  
  def game(): Unit = {
    // Logique de mouvement basée sur l'état des touches
    if (directionHaut) posY -= 5
    if (directionBas) posY += 5
    if (directionDroite) posX += 5
    if (directionGauche) posX -= 5

    //Controle des bords
    if (posY >= 360) posY = 360
    if (posY <= 0) posY = 0
    if (posX >= 360) posX = 360
    if (posX <= 0) posX = 0

    // Dessin
    display.frontBuffer.synchronized {
      display.clear()
      display.setColor(Color.BLUE)
      display.drawFillRect(posX, posY, 40, 40)
    }
    display.syncGameLogic(20) // 50 FPS
  }
  }



