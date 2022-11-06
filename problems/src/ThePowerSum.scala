// https://www.hackerrank.com/challenges/the-power-sum/problem
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

object ThePowerSumResult {

  /*
   * Complete the 'powerSum' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER X
   *  2. INTEGER N
   */

  def powerSum(X: Int, N: Int): Int = {
    // Write your code here
    def go(X: Int, a: Int = 1): Int = {
      if(X < 0 || X < pow(a, N)) 0
      else if (X == 0 || X == pow(a, N)) 1
      else go(X - pow(a, N).toInt, a + 1) + go(X, a + 1)
    }

    go(X)
  }

}

object ThePowerSumSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val X = StdIn.readLine.trim.toInt

    val N = StdIn.readLine.trim.toInt

    val result = ThePowerSumResult.powerSum(X, N)

    println(result)
  }
}

