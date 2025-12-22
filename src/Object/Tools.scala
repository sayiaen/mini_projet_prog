package Object

object Tools {
  def random(min: Int, max: Int): Int = (Math.random() * (max - min) + min).toInt

}
