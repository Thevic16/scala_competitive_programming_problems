import scala.collection.immutable._
import scala.io._

// https://www.hackerrank.com/challenges/weighted-uniform-string/

object WeightedUniformStringsResult {

  /*
   * Complete the 'weightedUniformStrings' function below.
   *
   * The function is expected to return a STRING_ARRAY.
   * The function accepts following parameters:
   *  1. STRING s
   *  2. INTEGER_ARRAY queries
   */
  val letterWeights = Map[String, Int]("a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4, "e" -> 5, "f" ->6,
    "g" -> 7, "h" -> 8, "i"-> 9, "j" -> 10, "k" -> 11, "l" -> 12, "m" -> 13, "n" -> 14, "o" -> 15,
    "p" -> 16, "q" -> 17, "r" -> 18, "s" -> 19, "t" -> 20, "u" -> 21, "v" -> 22, "w" -> 23, "x" -> 24,
    "y" -> 25, "z" -> 26)

  def weightedUniformStrings(s: String, queries: Array[Int]): Array[String] = {
    // Write your code here
    val stringWeight: Set[Int] = getStringWeight(s)
    queries.map(query => if (stringWeight.contains(query)) "Yes" else "No")
  }

  def getStringWeight(s: String, streak: List[String] = List(), weight: Set[Int] = Set()): Set[Int] = {
    if(s.isEmpty) weight
    else if(streak.nonEmpty)
      if(s.head.toString == streak.head) getStringWeight(s.tail, streak :+ s.head.toString, weight + letterWeights(s.head.toString) * (streak.size + 1))
      else getStringWeight(s.tail, List() :+ s.head.toString, weight + letterWeights(s.head.toString))
    else getStringWeight(s.tail, streak :+ s.head.toString, weight + letterWeights(s.head.toString))
  }

}

object WeightedUniformStringsSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val s = StdIn.readLine

    val queriesCount = StdIn.readLine.trim.toInt

    val queries = Array.ofDim[Int](queriesCount)

    for (i <- 0 until queriesCount) {
      val queriesItem = StdIn.readLine.trim.toInt
      queries(i) = queriesItem
    }

    val result = WeightedUniformStringsResult.weightedUniformStrings(s, queries)

    println(result.mkString("\n"))

    //printWriter.close()
  }
}
