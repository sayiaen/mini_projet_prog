// Cette classe permet de gérer la Mystery Box


class MysteryBox(val grid: Grid, var enabled: Boolean = false) {
  var x: Int = 0
  var y: Int = 0


  def remove(): Unit = {
    grid.setCell(x, y, '_')
    enabled = false
  }

  def place(): Unit = {
    val coord = grid.findPlace()
    x = coord._1
    y = coord._2
    grid.setCell(x, y, '?')
    enabled = true
  }

}
