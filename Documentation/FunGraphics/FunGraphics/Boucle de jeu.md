# Boucle de jeu

```
import hevs.graphics.FunGraphics
import java.awt.Color // Indispensable pour gérer les couleurs

object testFunGraphics extends App {


  // 1. Définir les dimensions (Largeur, Hauteur)
  val WIDTH = 800
  val HEIGHT = 600

  // 2. Créer la fenêtre principale
  val display = new FunGraphics(WIDTH, HEIGHT, "Mon Titre")
  while(true) {
    display.frontBuffer.synchronized {
      display.clear()
      x += 2
      display.drawFillRect(x, 200, 30, 30)
    }
    display.syncGameLogic(20)
  }
  private var x = 0
}
```