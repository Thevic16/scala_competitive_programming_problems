import scala.io.StdIn

object Solution extends App{

  val sAndN = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
  val s: Int = sAndN(0)
  val n: Int = sAndN(1)

  def getXsAndYs(n: Int): Array[(Int, Int)] = {

    def go(n: Int, xsAndYs: Array[(Int, Int)] = Array()): Array[(Int, Int)] = {
      if(n <= 0) xsAndYs
      else {
        val xAndY = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
        go(n-1, xsAndYs :+ (xAndY(0), xAndY(1)))
      }
    }

    go(n)
  }

  val xsAndYs: Array[(Int, Int)] = getXsAndYs(n).sortBy(_._1)


  println(s)
  println(n)
  println(xsAndYs.mkString("Array(", ", ", ")"))


  def play(s: Int, xsAndYs: Array[(Int, Int)]): Unit = {
    if(xsAndYs.isEmpty) println("YES")
    else if(s <= 0) println("NO")
    else {
      
    }
  }


}
