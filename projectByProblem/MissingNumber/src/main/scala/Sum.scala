import scala.io.StdIn.readLine

object Sum {
  def main(args: Array[String]): Unit = {
    val Array(a,b) = readLine.split(" ").map(_.toInt)
    println(a+b)
  }
}