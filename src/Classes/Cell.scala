package Classes
import java.awt.Color

class Cell(var cellType: Char = '_') {


  def getColor: Color = cellType match {
    case 'T' => Color.BLUE
    case 'C' => Color.GREEN
    case 'P' => Color.RED
    case '#' => Color.DARK_GRAY
    case _   => Color.WHITE
  }

}
