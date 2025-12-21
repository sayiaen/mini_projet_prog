import Classes.Grid

class Game {

  val grid = new Grid()
  val snakeDisplay = new Display(grid)

  // Variables d'état pour les directions
  var goingUp = false
  var goingDown = false
  var goingLeft = false
  var goingRight = false
  var posX = 200
  var posY = 200

  grid.printGrid
    while(true) {
      if(goingUp)
        snakeDisplay.render()
      snakeDisplay.fg.syncGameLogic(20)
  }

}
