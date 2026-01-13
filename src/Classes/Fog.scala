


class Fog(grid: Grid,   var enabled: Boolean = false, var distance: Int = 10, var duration: Int = 10) {

  val timer: Chronometer = new Chronometer(duration, "sec")

  def enable(duration: Int = 10, dist: Int = 10) : Unit= {
    timer.reset(duration)
    distance = dist
    enabled = true
  }


  def disable(): Unit = {
    enabled = false
    grid.allVisible()
  }

  def update(x: Int, y: Int): Unit = {
    if(enabled) {
      if(timer.checkTick()) disable()
      else grid.updateFog(x, y, distance)
    }
    else grid.allVisible()

  }

}
