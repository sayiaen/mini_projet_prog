import Classes.Grid
import hevs.graphics.FunGraphics

import java.awt.Color

class Display(var grid: Grid) {
  //Constantes
  private val HEIGHT = 800
  private val WIDTH = 800
  val fg = new FunGraphics(WIDTH, HEIGHT)
  val keyInput = new Keyboard(fg)

  //Variables


  def render(): Unit = {
    fg.clear()
    for (x <- 0 until grid.SIZE; y <- 0 until grid.SIZE) {
      drawGrid(x, y, grid.getCell(x, y).getColor)
    }
  }

  def drawGrid(x: Int, y: Int, c: Color) = {
    val posX: Int = x * grid.CELL_SIZE
    val posY: Int = y * grid.CELL_SIZE
    fg.setColor(c)
    fg.drawFillRect(posY, posX, grid.CELL_SIZE, grid.CELL_SIZE)
  }

}




