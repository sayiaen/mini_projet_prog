// Cette classe permet de gérer la grille qui contient les différentes valeurs du jeu


import Utils.SnakeFile
import Utils.Tools.random

class Grid() {
  val SIZE: Int = 40
  val CELL_SIZE: Int = 40
  var grid: Array[Array[Cell]] = Array.fill(SIZE, SIZE)(new Cell)
//  this.setCell(20,21,'T')
//  this.setCell(20, 22, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
//  this.setCell(20, 23, 'O', 2)
//  this.setCell(20, 23, 'O', 2)


  //permet de configuré une cellule spécifique

  def setCell(row: Int, col: Int,  cellType: Char, visible: Boolean = true, ttl: Int = -1, direction: Int = -1) = {
    grid(row)(col).visible = visible
    grid(row)(col).cellType = cellType
    grid(row)(col).ttl = ttl
    grid(row)(col).direction = direction

  }

  //permet d'afficher la grille de jeu dans la console

  def printGrid: Unit = {
    for(x <- 0 until SIZE) {
      for(y <- 0 until SIZE) {
            print(grid(x)(y).cellType.toString + " ")
      }
      println()
    }
  }

  //permet de mettre a jour la grille
  def updateGrid() = {
    for(x <- 0 until SIZE; y <- 0 until SIZE) {
     grid(x)(y).updateTTL()
    }
  }

  //definit toutes les cellule de la grille comme visible

  def allVisible(): Unit = {
    for(x <- 0 until SIZE; y <- 0 until SIZE) {
      grid(x)(y).setVisible()
    }
  }

  //definit toutes les cellule de la grille comme invisible
  def allUnvisible(): Unit = {
    for(x <- 0 until SIZE; y <- 0 until SIZE) {
      grid(x)(y).setUnvisible()
    }
  }


  //permet de mettre a jour les cellules concerné par le fog
  def updateFog(x0: Int, y0: Int, p: Int): Unit = {
    allUnvisible()
    var x1: Int = if(x0-p >= 0) x0 - p else 0
    var y1: Int = if(y0-p >= 0) y0 - p else 0
    var x2: Int = if(x0+p <= SIZE) x0 + p else SIZE
    var y2: Int = if(y0+p <= SIZE) y0 + p else SIZE

    for(x <- x1 until x2; y <- y1 until y2) {
      grid(x)(y).setVisible()
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

  // permet de charger une grille dans le dossier level


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

  def findPlace(): (Int, Int) = {
    var x = -1
    var y = -1
    var found: Boolean = false
    while (!found) {
      x = random(0, SIZE)
      y = random(0, SIZE)
      if (getCell(x, y).cellType == '_') found = true
    }
    (x, y)
  }

}

