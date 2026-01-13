

class Food(val grid: Grid) {
  var x: Int = 0
  var y: Int = 0


  def remove(): Unit = {
    grid.setCell(x, y, '_')
  }


  def place(): Unit = {
    val coord = grid.findPlace()
    x = coord._1
    y = coord._2
    grid.setCell(x, y, 'F')
  }

}
