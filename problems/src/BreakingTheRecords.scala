import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object BreakingTheRecordsResult {

  /*
   * Complete the 'breakingRecords' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts INTEGER_ARRAY scores as parameter.
   */

  def breakingRecords(scores: Array[Int]): Array[Int] = {
    // Write your code here
    def helper(scores: Array[Int], max: Int, min: Int, count: Array[Int]): Array[Int]  = {
      if (scores.isEmpty) count
      else if (scores.head > max) {
        count(0) += 1
        helper(scores.tail, scores.head, min, count)
      }
      else if (scores.head < min) {
        count(1) += 1
        helper(scores.tail, max, scores.head , count)
      }
      else helper(scores.tail, max, min , count)
    }

    helper(scores.tail, scores.head, scores.head, Array(0,0))
  }

}

object BreakingTheRecordsSolution {
  def main(args: Array[String]) {
    val n = StdIn.readLine.trim.toInt

    val scores = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val result = BreakingTheRecordsResult.breakingRecords(scores)

    println(result.mkString(" "))
  }
}
