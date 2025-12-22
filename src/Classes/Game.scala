import Classes.Grid

class Game {

  val grid = new Grid()
  val disp = new Display(grid)
  val spd = new GameSpeed(150)

  val LOGIC_SPEED: Int = 200 // temps du jeu en milliseconde

  // Variables d'état pour les directions
  var isAlive: Boolean = true
  var direction: Int = 1
  var speed: Int = 1
  var length = 3
  var command: Int = 0
  var posX = 20
  var posY = 20



  grid.printGrid
    while(true) {

      if(spd.checkTick()) {
        grid.updateGrid()
        if(disp.keyInput.isSpacePressed) length += 1
        if(isAlive) moveSnake() else   grid.end()

      }
      disp.refresh


  }

  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    def commandSnake(): Int = {
      var outCommand: Int = direction
      var directionChanged: Boolean = false
      if (disp.keyInput.isUpPressed) outCommand = 1
      if (disp.keyInput.isDownPressed) outCommand = 3
      if (disp.keyInput.isLeftPressed) outCommand = 4
      if (disp.keyInput.isRightPressed) outCommand = 2
      if(outCommand != direction) directionChanged = true
      if (directionChanged) outCommand
      else direction
    }

    def nextDirection(): Int = {
      var nextDirection = commandSnake()
      if(Math.abs(nextDirection - direction) == 2) nextDirection = direction
      nextDirection
    }

    def moveSnake(): Unit = {
      grid.setCell(posX, posY, 'O', length)
        nextDirection() match {
        case 1 => posX = posX - speed
        case 2 => posY = posY + speed
        case 3 => posX = posX + speed
        case 4 => posY = posY - speed
        case _ => None
      }
      if(posX < 0) posX = grid.SIZE-1
      if(posX >= grid.SIZE) posX = 0
      if (posY < 0) posY = grid.SIZE-1
      if (posY >= grid.SIZE) posY = 0

      grid.getCell(posX, posY).cellType match {
        case 'O' => isAlive = false; println("Tu as touché ton corps")
        case 'F' => length += 1; println("Tu as mangé une pomme")
        case  _ => None
      }
      if(isAlive) {
        grid.setCell(posX, posY, 'T')
        direction = nextDirection()
      }
      else {
          speed = 0
          println("Tu es mort")
      }
  }

}



class GameSpeed(val LOGIC_SPEED: Int) {

  var lastTime = System.currentTimeMillis()

  def checkTick(): Boolean = {
    if(System.currentTimeMillis() - lastTime > LOGIC_SPEED) {
      lastTime = System.currentTimeMillis()
      true
    }
    else false
  }
}


//        if(disp.keyInput.isUpPressed) posY -= 1
//        if(disp.keyInput.isDownPressed) posY += 1
//        if(disp.keyInput.isLeftPressed) posX -= 1
//        if(disp.keyInput.isRightPressed) posX += 1
