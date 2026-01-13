

class Chronometer(var initialIntervalle: Int = 200, var unit: String = "millis") {

  var initTime = now()
  var lastTime = now()
  var intervalle: Long = toMillis(initialIntervalle)

  private def now(): Long = System.currentTimeMillis()

  private def toMillis(v: Int): Long = {
    if (unit == "sec") v * 1000L else v.toLong
  }

  def checkTick(): Boolean = {
    if (now() - lastTime > intervalle) {
      lastTime = now()
      true
    }
    else false
  }

  def reset(x: Int): Unit = {
    initTime = now()
    lastTime = now()
    intervalle = toMillis(x)

  }

  def change(x: Int): Unit = {
    intervalle += toMillis(x)

  }

  def multiply(factor: Double): Unit = {
    val minLimit: Long = 50
    val diff: Long = intervalle - minLimit
    if(diff > 0) {

      intervalle = minLimit + (diff * factor).toLong
    }
    else
      intervalle = minLimit

  }

  def checkRandomTick(min: Int, max: Int): Boolean = {
    if (now() - lastTime > intervalle) {
      lastTime = now()
      intervalle = toMillis(Utils.Tools.random(min, max))
      true
    }
    else false
  }

}
