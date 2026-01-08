package Classes
import java.awt.Color

class Cell(var cellType: Char = '_', var ttl: Int = -1) {
  

  def updateTTL() =  {
    if(cellType == 'O') {
      if(ttl >= 1) ttl = ttl - 1
      else cellType = '_'
    }

  }

}
