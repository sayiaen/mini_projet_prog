package Classes

class Grid {
  val SIZE = 40
  val CELL_SIZE = 20
  var grid: Array[Array[Cell]] = Array.fill(SIZE, SIZE)(new Cell)
  this.setCell(20,21,'T')

  def setCell(x: Int, y: Int, cellType: Char) = {
    grid(y)(x).cellType = cellType
  }

  def printGrid: Unit = {
    for(x <- 0 until SIZE) {
      for(y <- 0 until SIZE) {
            print(grid(x)(y).cellType.toString + " ")
      }
      println()
    }
  }


  def getCell(x: Int, y: Int): Cell = grid(x)(y)
}
