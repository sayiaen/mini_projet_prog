import Classes.Grid

class Game {

  val grid = new Grid()
  val disp = new Display(grid)
  val spd = new GameSpeed(150)

  val LOGIC_SPEED: Int = 200 // temps du jeu en milliseconde

  // Variables d'état pour les directions
  var direction: Int = 0
  var speed: Int = 1
  var command: Int = 0
  var posX = 20
  var posY = 20



  grid.printGrid
    while(true) {
      if(spd.checkTick()) {
        moveSnake()

      }
      disp.refresh

  }

    def commandSnake(): Int = {
      if (disp.keyInput.isUpPressed) direction = 1
      if (disp.keyInput.isDownPressed) direction = 3
      if (disp.keyInput.isLeftPressed) direction = 4
      if (disp.keyInput.isRightPressed) direction = 2
      direction
    }

    def nextDirection(): Int = {
      var nextDirection = commandSnake()
      if(Math.abs(nextDirection - direction) == 2) nextDirection = direction
      nextDirection
    }

    def moveSnake(): Unit = {
      direction = nextDirection()
      direction match {
        case 1 =>
        case 2 =>
        case 3 =>
        case 4 =>
        case _ => None
      }

      grid.setCell(posX, nexY, 'T')

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
