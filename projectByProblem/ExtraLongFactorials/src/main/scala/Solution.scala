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

// https://www.hackerrank.com/challenges/extra-long-factorials/problem?isFullScreen=false

object Result {

  /*
   * Complete the 'extraLongFactorials' function below.
   *
   * The function accepts INTEGER n as parameter.
   */

  def extraLongFactorials(n: Int) {
    // Write your code here
    def go(n: Int, acc: BigInt = 1): BigInt = {
        if(n == 1) acc
        else go(n-1, acc*n)
    }

    println(go(n))
  }

}

object Solution {
  def main(args: Array[String]) {
    val n = StdIn.readLine.trim.toInt

    Result.extraLongFactorials(n)
  }
}
