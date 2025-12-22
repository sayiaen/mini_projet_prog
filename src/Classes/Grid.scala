package Classes

class Grid {
  val SIZE = 40
  val CELL_SIZE = 20
  var grid: Array[Array[Cell]] = Array.fill(SIZE, SIZE)(new Cell)
//  this.setCell(20,21,'T')
//  this.setCell(20, 22, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
  this.setCell(10, 10, 'F', 2)

  def setCell(row: Int, col: Int, cellType: Char, ttl: Int = -1) = {
    grid(row)(col).cellType = cellType
    grid(row)(col).ttl = ttl

  }

  def printGrid: Unit = {
    for(x <- 0 until SIZE) {
      for(y <- 0 until SIZE) {
            print(grid(x)(y).cellType.toString + " ")
      }
      println()
    }
  }

  def updateGrid() = {
    for(x <- 0 until SIZE; y <- 0 until SIZE) {
     grid(x)(y).updateTTL()
    }
  }


  def getCell(row: Int, col: Int): Cell = grid(row)(col)

  def end() = {
    for (x <- 0 until SIZE; y <- 0 until SIZE) {
      grid(x)(y).cellType = '#'
    }
  }
}

