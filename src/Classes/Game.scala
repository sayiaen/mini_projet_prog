import Classes.Grid
import Classes.Cell
import Classes.Snake
import Utils.SnakeFile
import Utils.Tools.random

class Game {

  // Variables
  val grid = new Grid()
  val disp = new Display(grid)
  val spd = new GameSpeed(150)
  var score: Int = 0
  grid.grid = grid.loadGrid("level")
  val initCoord: (Int, Int) = findPlace()
  val snake = new Snake(grid, initCoord._1, initCoord._2, random(1, 4), 2)
  var inputNextDirection = snake.direction

  initLevel()

  SnakeFile.writeFile("src/Levels", "level2", grid.saveGrid()) //sauvegarde le niveau
  

  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def run(): Unit = {
    while (true) {
      checkInput()
      if (spd.checkTick()) {
        if (snake.isAlive) checkCollision(snake.move(inputNextDirection)) else grid.end()
        grid.updateGrid()
      }
      disp.refresh

    }
  }

  def initLevel(): Unit = {

    placeFood()
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
    if (disp.keyInput.isEPressed) spd.snakeSpeed -= 10
    if (disp.keyInput.isQPressed) spd.snakeSpeed += 10
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
    score += 1
    println(s"Score $score")
    placeFood()

  }

  //-----------------------------------------------------------------------------------------------------------------------------------------

  private def placeFood(): Unit = {
    val coord: (Int, Int) = findPlace()
    grid.setCell(coord._1, coord._2, 'F')
  }

  def findPlace(): (Int, Int) = {
    var x = -1
    var y = -1
    var found: Boolean = false
    while (!found) {
      x = random(0, grid.SIZE)
      y = random(0, grid.SIZE)
      if (grid.getCell(x, y).cellType == '_') found = true
    }
    (x, y)
  }



  class GameSpeed(var snakeSpeed: Int = 200) {

    var lastTime = System.currentTimeMillis()

    def checkTick(): Boolean = {
      if (System.currentTimeMillis() - lastTime > snakeSpeed) {
        lastTime = System.currentTimeMillis()
        true
      }
      else false
    }
  }
}