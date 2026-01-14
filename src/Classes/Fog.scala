// Cette classe permet de gérer le brouillard


class Fog(grid: Grid,   var enabled: Boolean = false, var distance: Int = 10, var duration: Int = 10) {

  val timer: Chronometer = new Chronometer(duration, "sec") //permet de gérer la durée du fog

  
  //activer le fog
  def enable(duration: Int = 10, dist: Int = 10) : Unit= {
    timer.reset(duration)
    distance = dist
    enabled = true
  }

//désactiver le fog
  def disable(): Unit = {
    enabled = false
    grid.allVisible()
  }


// mise a jour du fog
  def update(x: Int, y: Int): Unit = {
    if(enabled) {
      if(timer.checkTick()) disable()
      else grid.updateFog(x, y, distance)
    }
    else grid.allVisible()

  }

}
