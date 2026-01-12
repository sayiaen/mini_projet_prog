class Button(val x1: Int, val y1: Int, val x2: Int, val y2: Int, val label: String) {

  def isClicked(mouse: Mouse): Boolean = {
    if(mouse.hasClicked) {
      if(mouse.posx >= x1 && mouse.posx <= x2 &&
         mouse.posy >= y1 && mouse.posy <= y2) {
        mouse.hasClicked = false
        return true
      }
    }
    return false
  }

}
