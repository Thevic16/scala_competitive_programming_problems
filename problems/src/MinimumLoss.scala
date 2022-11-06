// https://www.hackerrank.com/challenges/minimum-loss/problem
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

object MinimumLossResult {

  /*
   * Complete the 'minimumLoss' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts LONG_INTEGER_ARRAY price as parameter.
   */

  def minimumLoss(prices: Array[Long]): Int = {
    // Write your code here

    def go(priceBuy: Long, prices: Array[Long], losses: Array[Long]): Array[Long] = {
     if(prices.isEmpty) losses
     else go(prices.head, prices.tail, losses ++ prices.map(pricesSell => priceBuy - pricesSell))
    }

    val losses = go(prices.head, prices.tail, Array[Long]()).filter(_ > 0)
    losses.min.toInt
  }

}

object MinimumLossSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = StdIn.readLine.trim.toInt

    val price = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toLong)

    val result = MinimumLossResult.minimumLoss(price)

    println(result)

    //printWriter.close()
  }
}


