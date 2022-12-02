
import scala.io._


object CutTheSticksResult {

  /*
   * Complete the 'cutTheSticks' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

  def cutTheSticks(arr: Array[Int]): Array[Int] = {
    // Write your code here
    def go(arr: Array[Int], sticksCut: Array[Int] = Array()): Array[Int] = {
      if(arr.isEmpty) sticksCut
      else go(arr.map(_ - arr.min).filter(_ > 0), sticksCut :+ arr.length)
    }

    go(arr)
  }

}

object CutTheSticksSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = StdIn.readLine.trim.toInt

    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val result = CutTheSticksResult.cutTheSticks(arr)

    println(result.mkString("\n"))

    //printWriter.close()
  }
}
