

class Snake(val grid: Grid, var posX: Int = 20, var posY: Int = 20, var direction: Int = 1,  var length: Int = 1) {
  var isAlive: Boolean = true
  grid.setCell(posX, posY, 'T')

  def commandSnake(inputNextDirection: Int): Int = {
    var directionChanged: Boolean = false
    if (inputNextDirection != direction) directionChanged = true
    if (directionChanged) inputNextDirection
    else direction
  }

  def nextDirection(inputNextDirection: Int): Int = {
    var nextDirection = commandSnake(inputNextDirection)
    if (Math.abs(nextDirection - direction) == 2) nextDirection = direction
    nextDirection
  }
//Appele par la classe Game dans la boucle
  def move(inputNextDirection: Int = direction): (Int, Int, Int) = {
    var nextPosX: Int = posX
    var nextPosY: Int = posY

    nextDirection(inputNextDirection) match {

      case 1 => nextPosX = nextPosX - 1
      case 2 => nextPosY = nextPosY + 1
      case 3 => nextPosX = nextPosX + 1
      case 4 => nextPosY = nextPosY - 1
      case _ => None
    }
    if (nextPosX < 0) nextPosX = grid.SIZE - 1
    if (nextPosX >= grid.SIZE) nextPosX = 0
    if (nextPosY < 0) nextPosY = grid.SIZE - 1
    if (nextPosY >= grid.SIZE) nextPosY = 0

    (nextPosX, nextPosY, inputNextDirection)
    }

  def grow(qty: Int): Unit = {
    println("Tu as mangé une pomme")
    length += qty
  }

  def die: Unit = {
      println("RIP")
      isAlive = false
    }
      //Appeler par la classe Game
    def moveToNextPos(nextX: Int, nextY: Int, nextDir: Int): Unit = {
      val ttl = length
      direction = nextDirection(nextDir)
      grid.setCell(posX, posY, 'O', ttl)
      grid.setCell(nextX, nextY, 'T', -1, direction)

      posX = nextX
      posY = nextY
    }

  def reset(x: Int, y: Int, dir: Int, len: Int): Unit = {
    isAlive = true
     posX= x
    posY = y
    direction= dir
    length= len

  }
    
}


