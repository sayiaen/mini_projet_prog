// Cette classe permet de gérern la Food

class Food(val grid: Grid) {
  var x: Int = 0
  var y: Int = 0

//supprimer la food de la grille
  def remove(): Unit = {
    grid.setCell(x, y, '_')
  }

//placer la nourriture sur la grille
  def place(): Unit = {
    val coord = grid.findPlace()
    x = coord._1
    y = coord._2
    grid.setCell(x, y, 'F')
  }

}
