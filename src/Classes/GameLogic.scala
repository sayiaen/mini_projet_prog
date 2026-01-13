

import Utils.{SnakeFile, Tools}
import Utils.Tools.random

import scala.concurrent.duration.Duration

class GameLogic(val disp: Display, val grid: Grid) {

  val food: Food = new Food(grid)
  val box: MysteryBox = new MysteryBox(grid)

  var initCoord: (Int, Int) = (0, 0)
  var inputNextDirection: Int = _
  var multipyFactor: Double = 1


  var score: Int = 0
  var spd: Chronometer = new Chronometer(80)
  var difficultyTimer: Chronometer = new Chronometer(1, "sec")
  var boostTimer: Chronometer = new Chronometer(10, "sec")
  var snake: Snake = new Snake(grid, initCoord._1, initCoord._2, random(1, 4), 2)
  var boxTimer: Chronometer = new Chronometer(5, "sec")
  var fog: Fog = new Fog(grid)
  var boostEnabled: Boolean = false
  var speed: Long = spd.intervalle
  fog.enable()



  def updateGame(): String = {
    checkInput()
    if (spd.checkTick()) {
      fog.update(snake.posX, snake.posY)
      if (snake.isAlive) checkCollision(snake.move(inputNextDirection)) else {
        grid.end()
        return "gameover"
      }
      grid.updateGrid()
    }

    if(boostTimer.checkTick() && boostEnabled) disableBoost()
    if (difficultyTimer.checkTick()) manageSpeed()
    if(boxTimer.checkRandomTick(5,10)) manageBox()


    "playing"
  }

  def manageSpeed(): Unit = {
    spd.multiply(multipyFactor)

  }

  def manageBox(): Unit = {
    if (box.enabled) {
      box.remove()
    }
    else {
      box.place()
    }
  }

  def initGame(level: String, difficulty: String): Unit = {

    var nbWall: Int = 0

    difficulty match {
      case   "easy" =>
      nbWall = random(0,5)
        multipyFactor = 0.99
      case "medium" =>
      nbWall = random(5,15)
        multipyFactor = 0.98
      case "hard" =>
    nbWall = random(15,30)
        multipyFactor = 0.97

    }

    grid.grid = grid.loadGrid(level)
    food.place()
    fog.disable()
    disableBoost()
    placeRandomWall(nbWall)
    spd.reset(150)
    difficultyTimer.reset(1)
    score = 0
    initCoord = grid.findPlace()
    snake.reset(initCoord._1, initCoord._2, random(1, 4), 2)
    inputNextDirection = snake.direction
    SnakeFile.writeFile("src/Levels", "last_level", grid.saveGrid()) //sauvegarde le niveau
    grid.printGrid

  }


  def lotOfFood(): Unit = {
    val foodArray: Array[Food] = Array.fill(random(5, 20))(new Food(grid))
    for(f <- foodArray) {
      f.place()
    }
  }



  def checkInput() = {
    inputNextDirection = inputDirection()
    if (disp.keyInput.isSpacePressed) enableBoost(10)
    if (disp.keyInput.isEPressed)     spd.multiply(0.95)
    if (disp.keyInput.isQPressed)     spd.multiply(1.05)
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
      case 'O' | '#' =>
        snake.die
      case 'F' =>
        foodEaten()
        snake.moveToNextPos(pos._1, pos._2, pos._3)
      case '_' =>
        snake.moveToNextPos(pos._1, pos._2, pos._3)
      case '?' =>
        boxEaten()
        snake.moveToNextPos(pos._1, pos._2, pos._3)
      case _ => None
    }
  }

  def boxEaten(): Unit = {
    box.remove()
    val random = Utils.Tools.random(1,5)
    random match {
      case 1 =>
        println("length / 2")
        if(snake.length >= 4) snake.length /= 2
      case 2 =>
        enableBoost(Tools.random(5,15))
      case 3 =>
        println("lot of food")
        lotOfFood()
      case 4 =>
        println("fog")
        fog.enable(Tools.random(10, 20),Tools.random(5, 15))
      case 5 => snake.length += 5

    }

  }
  def enableBoost(duration: Int) = {
    if (!boostEnabled) {
      speed = spd.intervalle
      spd.intervalle = 20
      boostEnabled = true
    }
    boostTimer.reset(duration)
    println("BOOST ENABLED")
  }

  def disableBoost() = {
    boostEnabled = false
    spd.intervalle = speed
    println("END OF BOOST")
  }

  def foodEaten() = {
    food.remove()
    snake.grow(1)
    score += 1
    println(s"Score $score")
    food.place()
  }


  def placeRandomWall(max: Int = 20) = {
    for (i <- 0 until max) {
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


}
