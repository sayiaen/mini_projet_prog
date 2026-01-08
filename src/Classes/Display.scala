import Classes.{Cell, Grid, SnakeImage}
import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

class Display(var grid: Grid) {
  //Constantes
  private val HEIGHT = 1600
  private val WIDTH = 1600
  val fg = new FunGraphics(WIDTH, HEIGHT)
  val keyInput = new Keyboard(fg)

  val headImg: SnakeImage = new SnakeImage("/Ressources/head.png", grid.CELL_SIZE)
  val wallImg: SnakeImage = new SnakeImage("/Ressources/wall.jpg", grid.CELL_SIZE)
  val bgImg: SnakeImage = SnakeImage("/Ressources/bg.jpg", grid.CELL_SIZE)
  val foodImg: SnakeImage = SnakeImage("/Ressources/food.png", grid.CELL_SIZE)
  val bodyImg: SnakeImage = SnakeImage("/Ressources/body.png", grid.CELL_SIZE)



  def cellToColor(cell: Cell): Color = {

    cell.cellType match {
      case 'T' => Color.BLUE
      case 'O' => Color.GREEN
      case 'F' => Color.RED
      case '#' => Color.DARK_GRAY
      case '_' => Color.WHITE
      case _ => Color.WHITE
    }

  }

  def cellToImage(cell: Cell): SnakeImage = {
    cell.cellType match {
      case 'T' => headImg
      case 'O' => bodyImg
      case 'F' => foodImg
      case '#' => wallImg
      case '_' => bgImg
      case _ => bgImg
    }

  }

  def refresh = {
//    renderColor()
    renderImage()
    fg.syncGameLogic(60)
  }


  def renderColor(): Unit = {
    fg.clear()
    for (x <- 0 until grid.SIZE; y <- 0 until grid.SIZE) {
      drawGridColor(x, y, cellToColor(grid.getCell(x, y)))
    }
  }

  def renderImage(): Unit = {
    fg.clear()
    for (x <- 0 until grid.SIZE; y <- 0 until grid.SIZE) {
      drawGridImage(x, y, cellToImage(grid.getCell(x, y)))
    }
  }

  def drawGridColor(x: Int, y: Int, c: Color) = {
    val posX: Int = x * grid.CELL_SIZE
    val posY: Int = y * grid.CELL_SIZE
    fg.setColor(c)
    fg.drawFillRect(posY, posX, grid.CELL_SIZE, grid.CELL_SIZE)
  }

  def drawGridImage(x: Int, y: Int, img: SnakeImage) = {
    val drawX = y * grid.CELL_SIZE
    val drawY = x * grid.CELL_SIZE

    img.draw(fg, drawX, drawY)
  }


}




