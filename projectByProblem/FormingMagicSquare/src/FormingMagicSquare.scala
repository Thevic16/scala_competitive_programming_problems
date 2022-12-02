import scala.annotation.tailrec
import scala.io._



object FormingMagicSquareResult {

  /*
   * Complete the 'formingMagicSquare' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts 2D_INTEGER_ARRAY s as parameter.
   */

  val allPossiblesMagicSquares: Array[Array[Array[Int]]] = Array(
    Array(Array(2,7,6), Array(9,5,1), Array(4,3,8)),
    Array(Array(2,9,4), Array(7,5,3), Array(6,1,8)),
    Array(Array(4,3,8), Array(9,5,1), Array(2,7,6)),
    Array(Array(4,9,2), Array(3,5,7), Array(8,1,6)),
    Array(Array(6,1,8), Array(7,5,3), Array(2,9,4)),
    Array(Array(6,7,2), Array(1,5,9), Array(8,3,4)),
    Array(Array(8,3,4), Array(1,5,9), Array(6,7,2)),
    Array(Array(8,1,6), Array(3,5,7), Array(4,9,2)),
  )


  def formingMagicSquare(s: Array[Array[Int]]): Int = {
    // Write your code here
    @tailrec
    def go(allPossiblesMagicSquares: Array[Array[Array[Int]]], minCost: Int = Int.MaxValue): Int = {
      if(allPossiblesMagicSquares.isEmpty) minCost
      else go(allPossiblesMagicSquares.tail, math.min(minCost,
        getCost(allPossiblesMagicSquares.head.flatten, s.flatten)))
    }

    go(allPossiblesMagicSquares)
  }

  def getCost(s1: Array[Int], s2: Array[Int], cost: Int = 0): Int = {
    if(s1.isEmpty || s2.isEmpty) cost
    else getCost(s1.tail, s2.tail, cost + (s1.head - s2.head).abs)
  }

}

object Solution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val s = Array.ofDim[Int](3, 3)

    for (i <- 0 until 3) {
      s(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }

    val result = FormingMagicSquareResult.formingMagicSquare(s)

    println(result)

    //printWriter.close()
  }
}

