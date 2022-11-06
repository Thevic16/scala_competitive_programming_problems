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

object CompareTheTripletsResult {

  /*
   * Complete the 'compareTriplets' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY a
   *  2. INTEGER_ARRAY b
   */

  def compareTriplets(a: Array[Int], b: Array[Int]): Array[Int] = {
    // Write your code here
    def helper(a: Array[Int], b: Array[Int], scores: Array[Int]): Array[Int]  = {
      if (a.isEmpty && b.isEmpty) scores
      else if (a.head > b.head){
        scores(0) += 1
        helper(a.tail, b.tail, scores)
      }
      else if (a.head < b.head){
        scores(1) += 1
        helper(a.tail, b.tail, scores)
      }
      else helper(a.tail, b.tail, scores)
    }

      helper(a, b, Array(0, 0))
  }

}

object CompareTheTripletsSolution {
  def main(args: Array[String]) {
    val a = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val b = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val result = CompareTheTripletsResult.compareTriplets(a, b)

    println(result.mkString(" "))

  }
}

