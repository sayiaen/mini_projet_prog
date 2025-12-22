package Classes

class Snake(val grid: Grid, var posX: Int = 20, var posY: Int = 20) {
  var isAlive: Boolean = true
  var direction: Int = 1
  var speed: Int = 1
  var length = 3

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

  def move(inputNextDirection: Int = direction): (Int, Int, Int) = {
    grid.setCell(posX, posY, 'O', length)
    nextDirection(inputNextDirection) match {
      case 1 => posX = posX - speed
      case 2 => posY = posY + speed
      case 3 => posX = posX + speed
      case 4 => posY = posY - speed
      case _ => None
    }
    if (posX < 0) posX = grid.SIZE - 1
    if (posX >= grid.SIZE) posX = 0
    if (posY < 0) posY = grid.SIZE - 1
    if (posY >= grid.SIZE) posY = 0

    (posX, posY, inputNextDirection)
    }

  def grow(qty: Int): Unit = {
    length += qty
  }

  def die(): Unit =
    {
      isAlive = false
    }

  }

