import Classes.{Grid, Snake}
import Object.Tools.random

class Game {
  val grid = new Grid()
  val disp = new Display(grid)
  val spd = new GameSpeed(150)
  val snake = new Snake(grid, random(0, grid.SIZE), random(0, grid.SIZE), random(1,4))

  grid.setCell(random(0, grid.SIZE), random(0, grid.SIZE), 'F')

  // Variables d'état pour les directions
  var command: Int = 0


  grid.printGrid
  while (true) {
    if (spd.checkTick()) {
      grid.updateGrid()
      if (disp.keyInput.isSpacePressed) snake.length += 1
      if (snake.isAlive) checkCollision(snake.move(inputDirection())) else grid.end()
    }
    disp.refresh


  }

  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
      case 'O' => snake.die
      case 'F' => foodEaten()
      case _ => None
    }
    if (snake.isAlive) {
      grid.setCell(pos._1, pos._2, 'T')
      snake.direction = snake.nextDirection(pos._3)
    }
  }

  def foodEaten() = {
    snake.grow(1)
    grid.setCell(random(0, grid.SIZE-1), random(0, grid.SIZE), 'F')

  }

  //-----------------------------------------------------------------------------------------------------------------------------------------




  class GameSpeed(val LOGIC_SPEED: Int = 200) {

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