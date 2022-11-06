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

object NumberLineJumpsResult {

  /*
   * Complete the 'kangaroo' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   *  1. INTEGER x1
   *  2. INTEGER v1
   *  3. INTEGER x2
   *  4. INTEGER v2
   * https://www.hackerrank.com/challenges/kangaroo/problem
   */
  def verifyConditionBehind(x1: Int, v1: Int, x2: Int, v2: Int): Boolean = {
    if (x1 < x2 && v1 < v2) true
    else if (x2 < x1 && v2 < v1) true
    else false
  }

  def verifyNumberOFJumps(x1: Int, v1: Int, x2: Int, v2: Int): Boolean = {
     //case 1: Number of jumps is a integer
     if ((x1 - x2) % (v2 - v1) == 0) true
     //case 2: Number of jumps is a float
     else false
  }

  def kangaroo(x1: Int, v1: Int, x2: Int, v2: Int): String = {
    // Write your code here
    if (verifyConditionBehind(x1, v1, x2, v2)) "NO"
    else if (verifyNumberOFJumps(x1, v1, x2, v2)) "YES"
    else "NO"
  }

}

object NumberLineJumpsSolution {
  def main(args: Array[String]) {
    // val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val firstMultipleInput = StdIn.readLine.replaceAll("\\s+$", "").split(" ")

    val x1 = firstMultipleInput(0).toInt

    val v1 = firstMultipleInput(1).toInt

    val x2 = firstMultipleInput(2).toInt

    val v2 = firstMultipleInput(3).toInt

    val result = try {
      NumberLineJumpsResult.kangaroo(x1, v1, x2, v2)
    }catch {
      case e: ArithmeticException => "NO"
    }
    println(result)

    //printWriter.close()
  }
}
