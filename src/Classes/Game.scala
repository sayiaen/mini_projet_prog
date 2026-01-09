import Classes.Grid
import Classes.Cell
import Classes.Snake
import Utils.SnakeFile
import Utils.Tools.random


class Game {

  // Variables
  var state: String = "menu"

  val grid = new Grid()
  val disp = new Display(grid)
  var spd: GameSpeed = _
  var score: Int = 0
  var initCoord: (Int, Int) = _
  var snake: Snake = _
  var inputNextDirection: Int = _


  

  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def run(): Unit = {
    while (true) {
      state match {
        case "menu" =>
          updateMenu()
          disp.drawMenu
        case "playing" =>
          updateGame()
          disp.drawGame(score)
        case "gameover" =>
          updateGameOver()
          disp.drawGameOver

      }
      disp.fg.syncGameLogic(60)
    }
  }

  private def updateGameOver() = {
    if(disp.keyInput.isEnterPressed) {
      initGame("level1")
      SnakeFile.writeFile("src/Levels", "last_level", grid.saveGrid()) //sauvegarde le niveau
      state = "playing"
    }

  }

  private def updateMenu(): Unit = {

    if(disp.keyInput.isEnterPressed) {
      initGame("level1")
      SnakeFile.writeFile("src/Levels", "last_level", grid.saveGrid()) //sauvegarde le niveau
      state = "playing"
    }
  }

  private def updateGame(): Unit = {
    checkInput()
    if (spd.checkTick()) {
      if (snake.isAlive) checkCollision(snake.move(inputNextDirection)) else {
        grid.end()
        Thread.sleep(100)
        state = "gameover"
      }
      grid.updateGrid()
    }

  }


  def initGame(level: String): Unit = {
    spd = new GameSpeed(150)
    score = 0
    initCoord = findPlace()
    snake = new Snake(grid, initCoord._1, initCoord._2, random(1, 4), 2)
    inputNextDirection = snake.direction

    grid.grid = grid.loadGrid(level)
    placeFood()
    placeRandomWall()

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

  def placeRandomWall() = {
    for (i <- 0 until 20) {
      var x = -1
      var y = -1
      var found: Boolean = false
      while (!found) {
        x = random(0, grid.SIZE)
        y = random(0, grid.SIZE)
        if (grid.getCell(x, y).cellType == '_') found = true
      }

      grid.setCell(x, y, '#')
    }
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