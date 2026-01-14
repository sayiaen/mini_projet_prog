// Cette classe représente une cellule de la Grid

class Cell(var cellType: Char = '_', var ttl: Int = -1, var visible: Boolean = true, var direction: Int = -1) {

  def setVisible(): Unit = visible = true
  def setUnvisible(): Unit = visible = false

  
  //Permet au corps du Snake de se déplacer
  def updateTTL() =  {
    if(cellType == 'O') {
      if(ttl >= 1) ttl = ttl - 1
      else cellType = '_'
    }

  }

}
