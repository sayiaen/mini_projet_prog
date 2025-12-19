import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap
import java.awt.Color

object ImageExample extends App {
  val fg = new FunGraphics(300, 300, "With thread", true)

  // Image doit être dans le dossier /src/res/
  val bm = new GraphicsBitmap("/res/pngtree-snake-illustration-png-image_11743292.png")

  val scale = 0.25
  var angle = 0.1
  var offset = 0.01

  while (true) {
    // Logique : fait osciller l'angle entre -45° et +45°
    angle += offset
    if (angle > Math.PI / 4 || angle <= -Math.PI / 4) {
      offset *= -1
    }

    // Rendu avec synchronisation pour éviter le clignotement
    fg.frontBuffer.synchronized {
      fg.clear(Color.white)

      val x = fg.getFrameWidth / 2
      val y = fg.getFrameHeight / 2 - 25

      // Dessine l'image centrée avec rotation et échelle
      fg.drawTransformedPicture(x, y, angle, scale, bm)
    }

    // Maintient un débit de 60 images par seconde
    fg.syncGameLogic(60)
  }
}