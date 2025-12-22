import Classes.{Grid, Snake}
import Object.Tools.random

class Game {

  // Variables
  val grid = new Grid()
  val disp = new Display(grid)
  val spd = new GameSpeed(150)
  val snake = new Snake(grid, random(0, grid.SIZE), random(0, grid.SIZE), random(1, 4), 2)
  var inputNextDirection = snake.direction

  initLevel()



  while (true) {
    checkInput()
    if (spd.checkTick()) {
      if (snake.isAlive) checkCollision(snake.move(inputNextDirection)) else grid.end()
      grid.updateGrid()
    }
    disp.refresh

  }

  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def initLevel(): Unit = {
    grid.setCell(random(0, grid.SIZE), random(0, grid.SIZE), 'F')
    for (i <- 0 until 20) {
      var x = -1
      var y = -1
      var found: Boolean = false
      while (!found) {
        x = random(0, grid.SIZE)
        y = random(0, grid.SIZE)
        if(grid.getCell(x, y).cellType == '_') found = true
      }

      grid.setCell(x, y, '#')
    }
  grid.printGrid
  }


  def checkInput() = {
    inputNextDirection = inputDirection()
    if (disp.keyInput.isSpacePressed) snake.length += 1
    if (disp.keyInput.isEPressed) spd.LOGIC_SPEED -= 10
    if (disp.keyInput.isQPressed) spd.LOGIC_SPEED += 10
  }

  def inputDirection(): Int = {
    var outCommand = snake.direction
    if (disp.keyInput.isUpPressed) outCommand = 1
    if (disp.keyInput.isDownPressed) outCommand = 3
    if (disp.keyInput.isLeftPressed) outCommand = 4
    if (disp.keyInput.isRightPressed) outCommand = 2
    outCommand
  }

  def checkCollision(pos: (Int, Int, Int)): Unit = {
    grid.getCell(pos._1, pos._2).cellType match {
      case 'O' | '#' => snake.die
      case 'F' => foodEaten();    snake.moveToNextPos(pos._1, pos._2, pos._3)
      case '_' => snake.moveToNextPos(pos._1, pos._2, pos._3)
      case _ => None
    }
  }

  def foodEaten() = {
    snake.grow(1)
    grid.setCell(random(0, grid.SIZE-1), random(0, grid.SIZE), 'F')

  }

  //-----------------------------------------------------------------------------------------------------------------------------------------




  class GameSpeed(var LOGIC_SPEED: Int = 200) {

    var lastTime = System.currentTimeMillis()

    def checkTick(): Boolean = {
      if (System.currentTimeMillis() - lastTime > LOGIC_SPEED) {
        lastTime = System.currentTimeMillis()
        true
      }
      else false
    }
  }
}