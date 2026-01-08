package Classes
import hevs.graphics.utils.GraphicsBitmap
import hevs.graphics.FunGraphics

class SnakeImage(val path: String, val CELL_SIZE: Int) {
val bm = new GraphicsBitmap(path)
println("Creating new image")
val scale: Double = CELL_SIZE.toDouble / bm.getWidth

def draw(fg: FunGraphics, x: Int, y: Int): Unit = {

  val centerX = x + CELL_SIZE / 2
  val centerY = y + CELL_SIZE / 2

  fg.drawTransformedPicture(centerX, centerY, 0, scale, bm)
}

def getImage = bm
}
