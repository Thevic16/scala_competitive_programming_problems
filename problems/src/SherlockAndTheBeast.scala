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

/*
https://www.hackerrank.com/challenges/sherlock-and-the-beast/problem
* */

object SherlockAndTheBeastResult {

  /*
   * Complete the 'decentNumber' function below.
   *
   * The function accepts INTEGER n as parameter.
   */

  def decentNumber(n: Int): String =  {
    // Write your code here
    if (n % 3 == 0) "5" * n
    else if (n % 5 == 0) "3" * n
    else if (n % 3 == 2 && n >= 5) "5"*(n - 5) + "3"*5
    else "-1"
  }

}

object SherlockAndTheBeastSolution {
  def main(args: Array[String]) {
    val t = StdIn.readLine.trim.toInt

    for (tItr <- 1 to t) {
      val n = StdIn.readLine.trim.toInt

      println(SherlockAndTheBeastResult.decentNumber(n))
    }
  }
}
