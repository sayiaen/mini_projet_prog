import Classes.Grid

import scala.collection.mutable.Queue

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
  val snake = Queue[(Int, Int)]()
  var posX = 20
  var posY = 20
  var dirX  = 1
  var dirY = 0 // pour faire commncer / bouger vers droit
  snake.enqueue((posX,posY))
  grid.setCell(posX,posY, 'T')
  disp.refresh



  grid.printGrid
    while(true) {
      if(spd.checkTick()) {
        if(disp.keyInput.isUpPressed) {
         dirX = 0;  dirY = - 1
        }
        else if(disp.keyInput.isDownPressed){
          dirX = 0; dirY = 1
        }
        else if(disp.keyInput.isLeftPressed) {
          dirY  =0; dirX =  -1
        }
        else if(disp.keyInput.isRightPressed){
          dirY = 0; dirX = 1}

        posX += dirX
        posY += dirY



        snake.enqueue((posX, posY))
        grid.setCell(posX,posY , 'T')

        val(tailX, tailY) = snake.dequeue()
        grid.setCell(tailX,tailY, ' ')
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