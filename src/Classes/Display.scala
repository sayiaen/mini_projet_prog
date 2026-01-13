
import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

class Display(var grid: Grid) {
  //Constantes
  private val HEIGHT = 1800
  private val WIDTH = 1600
  val fg = new FunGraphics(WIDTH, HEIGHT, 0, 0, "Snake", true)
  val keyInput = new Keyboard(fg)
  val mouse = new Mouse(fg)

  val boxImg: SnakeImage = new SnakeImage("/Ressources/mysteryBox.png", grid.CELL_SIZE)
  val head_downImg: SnakeImage = new SnakeImage("/Ressources/head_down.png", grid.CELL_SIZE)
  val head_upImg: SnakeImage = new SnakeImage("/Ressources/head_up.png", grid.CELL_SIZE)
  val head_leftImg: SnakeImage = new SnakeImage("/Ressources/head_left.png", grid.CELL_SIZE)
  val head_rightImg: SnakeImage = new SnakeImage("/Ressources/head_right.png", grid.CELL_SIZE)
  val wallImg: SnakeImage = new SnakeImage("/Ressources/wall.png", grid.CELL_SIZE)
  val bgImg: SnakeImage = new SnakeImage("/Ressources/bg.png", grid.CELL_SIZE)
  val foodImg: SnakeImage = new SnakeImage("/Ressources/food.png", grid.CELL_SIZE)
  val bodyImg: SnakeImage = new SnakeImage("/Ressources/body.jpeg", grid.CELL_SIZE)
  val menu_gameover: SnakeImage = new SnakeImage("/Ressources/menu_gameover.png")
  val menu_screen: SnakeImage = new SnakeImage("/Ressources/menu_screen.png")
  val menu_settings: SnakeImage = new SnakeImage("/Ressources/menu_settings.png")
  val overlay: SnakeImage = new SnakeImage("/Ressources/overlay.png")


  def displayOverlay(score: Int) = {
    fg.frontBuffer.synchronized {
      fg.setColor(Color.white)
      fg.drawFillRect(0, 1600, 1800, 200)
      overlay.place(fg, 800, 1700)
      //    fg.setColor(Color.white)
      //    fg.drawFillRect(70, 1620, 400, 100)
      fg.drawString(277, 1750, s"$score", Color.white, 64)
    }

  }


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
      case 'T' => cell.direction match {
        case 1 => head_upImg
        case 2 => head_rightImg
        case 3 => head_downImg
        case 4 => head_leftImg
        case _ => head_upImg
      }
      case 'O' => bodyImg
      case 'F' => foodImg
      case '#' => wallImg
      case '?' => boxImg
      case '_' => bgImg
      case _ => bgImg
    }

  }

  def drawGame(score: Int) = {
    // renderColor()
    renderImage()
    displayOverlay(score)

  }

  def drawMenu = {
    fg.frontBuffer.synchronized {
      menu_screen.place(fg)
      //  fg.setColor(Color.white)
      //  fg.drawFillRect(0, 0, WIDTH, HEIGHT)
      //  fg.drawString(80, 1700, s"Appuyer enter pour continuer", Color.black, 72)
    }
  }

  def drawGameOver(score: Int) = {
    fg.frontBuffer.synchronized {
      //      fg.setColor(Color.white)
      //      fg.drawFillRect(0, 0, WIDTH, HEIGHT)
      //      fg.drawString(80, 1700, s"Tu as perdu", Color.black, 72)
      menu_gameover.place(fg)
      fg.drawString(800, 996, s"$score", Color.white, 72)
    }

  }

  def drawSettings = {
    fg.frontBuffer.synchronized {
      menu_settings.place(fg)

    }

  }

  def renderColor(): Unit = {

    for (x <- 0 until grid.SIZE; y <- 0 until grid.SIZE) {
      drawGridColor(x, y, cellToColor(grid.getCell(x, y)))
    }
  }

  def renderImage(): Unit = {
    for (x <- 0 until grid.SIZE; y <- 0 until grid.SIZE) {
      drawGridImage(x, y, bgImg)
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




