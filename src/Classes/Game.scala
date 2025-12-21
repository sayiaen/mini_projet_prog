import Classes.Grid

class Game {

  val grid = new Grid()
  val disp = new Display(grid)
  val spd = new GameSpeed(150)

  val LOGIC_SPEED: Int = 200 // temps du jeu en milliseconde

  // Variables d'état pour les directions
  var goingUp = false
  var goingDown = false
  var goingLeft = false
  var goingRight = false
  var posX = 20
  var posY = 20



  grid.printGrid
    while(true) {
      if(spd.checkTick()) {
        if(disp.keyInput.isUpPressed) posY -= 1
        if(disp.keyInput.isDownPressed) posY += 1
        if(disp.keyInput.isLeftPressed) posX -= 1
        if(disp.keyInput.isRightPressed) posX += 1

        grid.setCell(posX,posY , 'T')
      }
      disp.refresh

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