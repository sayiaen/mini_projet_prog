import scala.io.Source

object File {
  def readFile(path: String): String = {
    val file = Source.fromFile(path)
    file.getLines().toArray.mkString("$")
  }

}