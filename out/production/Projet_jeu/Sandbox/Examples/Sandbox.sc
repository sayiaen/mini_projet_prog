import Utils.Tools


def random(min: Int, max: Int): Int = (Math.random()*(max-min)+min).toInt


for(i <- 0 until 100) {
  println(random(8,10))
}