package Classes
import scala.util.Random


class Food() {

  def spawnFood(grid: Array[Array[Int]]): Unit = {
    val rand = new Random
    val rows = grid.length
    val cols = grid(0).length

    var placed = false

    while(!placed){
      val r = rand.nextInt(rows)
      val c = rand.nextInt(cols)

      if(grid(r)(c) == 0){
        grid(r)(c) = 2
        placed = true
      }
    }
    //on utilise le while pour parcourire le grid et dans les places vides on utilise le methode random pour mettre un food (=2)

  }
  }

