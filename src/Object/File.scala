import scala.io.Source
import java.io.{FileOutputStream, PrintWriter}


object File {
  def readFile(path: String): Option[Array[String]] = {
    try {
      val file = Source.fromFile(path)
      val out = file.getLines().toArray
      file.close()
      Some(out)
    } catch {
      case e: Exception => println("Error to access the file")
        None
    }
  }

  def writeFile(path: String, name: String, content: Array[String]) = {
    val fos = new FileOutputStream(s"$path/$name")
    val pw = new PrintWriter(fos)
    for(i <- content.indices) {
      pw.println(content(i))
    }
    pw.close
  }

}