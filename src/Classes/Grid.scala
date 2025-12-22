package Classes
import Utils.SnakeFile

class Grid() {
  val SIZE: Int = 40
  val CELL_SIZE: Int = 20
  var grid: Array[Array[Cell]] = Array.fill(SIZE, SIZE)(new Cell)
//  this.setCell(20,21,'T')
//  this.setCell(20, 22, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
//  this.setCell(20, 23, 'O', 2)


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

  def saveGrid(): Array[String] = {
    val out: Array[String] = Array.fill(SIZE)("")
    for (x <- 0 until SIZE) {
      for (y <- 0 until SIZE) {
        out(x) = out(x) + grid(x)(y).cellType.toString
      }

    }
    out
  }

  def loadGrid(name: String): Array[Array[Cell]] = {
    val out: Array[Array[Cell]]  = Array.fill(SIZE, SIZE)(new Cell())
    val file = SnakeFile.readFile(s"src/Levels/$name")
    file match {
      case Some(s) =>
        for (x <- 0 until SIZE) {
          for (y <- 0 until SIZE) {
            out(x)(y).cellType = s(x)(y).toChar
          }
    }
      case None =>
        println("Error to load the level")
  }

    out
  }


  def end() = {
    for (x <- 0 until SIZE; y <- 0 until SIZE) {
      grid(x)(y).cellType = '#'
    }
  }
}

