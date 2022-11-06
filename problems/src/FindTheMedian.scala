
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

// https://www.hackerrank.com/contests/club-programacion-competitiva-2do-contest-2do-semestre-07102022/challenges/find-the-median

object FindTheMedianResult {

  /*
   * Complete the 'findMedian' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

  def findMedian(arr: Array[Int]): Int = {
    // Write your code here
    val sortArr = arr.sorted
    val indMedia: Int = arr.size /2

    sortArr(indMedia)
  }

}

object FindTheMedianSolution {
  def main(args: Array[String]) {

    val n = StdIn.readLine.trim.toInt

    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val result = FindTheMedianResult.findMedian(arr)

    println(result)

    //printWriter.close()
  }
}

