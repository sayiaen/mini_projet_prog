package Classes
import java.awt.Color

class Cell(var cellType: Char = '_', var ttl: Int = -1) {


  def getColor: Color = cellType match {
    case 'T' => Color.BLUE
    case 'O' => Color.GREEN
    case 'F' => Color.RED
    case '#' => Color.DARK_GRAY
    case '_'   => Color.WHITE
    case _   => Color.WHITE
  }

  def updateTTL() =  {
    if(cellType == 'O') {
      if(ttl >= 1) ttl = ttl - 1
      else cellType = '_'
    }

  }

}
