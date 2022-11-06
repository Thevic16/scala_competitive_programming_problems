// https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem
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

object MinimumAbsoluteDifferenceResult {

  /*
   * Complete the 'minimumAbsoluteDifference' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

  def minimumAbsoluteDifference(arr: Array[Int]): Int = {
    // Write your code here
    val sortedArr = arr.sorted

    def go(head: Int, arr: Array[Int], differences: Array[Int]): Array[Int] ={
      if(arr.isEmpty) differences
      else go(arr.head, arr.tail, differences :+ abs(head - arr.head))
    }

    val differences = go(sortedArr.head, sortedArr.tail, Array[Int]())
    differences.min
  }

}

object MinimumAbsoluteDifferenceResultSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = StdIn.readLine.trim.toInt

    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val result = MinimumAbsoluteDifferenceResult.minimumAbsoluteDifference(arr)

    println(result)

    //printWriter.close()
  }
}
