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
   * Complete the 'bonAppetit' function below.
   *
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY bill
   *  2. INTEGER k
   *  3. INTEGER b
   */

  def bonAppetit(bill: Array[Int], k: Int, b: Int) {
    // Write your code here

    val fairlyB = (bill.sum - bill(k) ) / 2

    if(fairlyB == b){
      println("Bon Appetit")
    }
    else {
      println(b - fairlyB)
    }
  }
}

object Solution {
  def main(args: Array[String]) {
    val firstMultipleInput = StdIn.readLine.replaceAll("\\s+$", "").split(" ")

    val n = firstMultipleInput(0).toInt

    val k = firstMultipleInput(1).toInt

    val bill = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val b = StdIn.readLine.trim.toInt

    Result.bonAppetit(bill, k, b)
  }
}
