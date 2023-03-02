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

object Result {

  /*
   * Complete the 'miniMaxSum' function below.
   *
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

  def miniMaxSum(arr: Array[Long]) {
    // Write your code here
    val sortArr: Array[Long] = arr.sorted
    print(sortArr.take(4).sum + " ")
    print(sortArr.tail.take(4).sum)
  }

}

object Solution {
  def main(args: Array[String]) {

    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toLong)

    Result.miniMaxSum(arr)
  }
}
