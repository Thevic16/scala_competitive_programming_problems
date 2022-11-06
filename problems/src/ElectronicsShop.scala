// https://www.hackerrank.com/challenges/electronics-shop/problem
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object ElectronicsShopSolution {

  /*
   * Complete the getMoneySpent function below.
   */

  /*
  def getMoneySpent(keyboards: Array[Int], drives: Array[Int], b: Int): Int = {
    /*
     * Write your code here.
     */
    def go(keyboards: Array[Int], drives: Array[Int], moneySpent: Int = -1): Int = {
      if(moneySpent != -1) moneySpent
      else if (keyboards.isEmpty || drives.isEmpty) moneySpent
      else if (keyboards.head + drives.head > moneySpent && keyboards.head + drives.head <= b)
        max(go(keyboards.tail, drives, keyboards.head + drives.head), go(keyboards, drives.tail, keyboards.head + drives.head))
      else max(go(keyboards.tail, drives, moneySpent), go(keyboards, drives.tail, moneySpent))
    }

    go(keyboards, drives)
  }
   */

  /*
  def getMoneySpent(keyboards: Array[Int], drives: Array[Int], b: Int): Int = {
    /*
     * Write your code here.
     */
    def go(arrayA: Array[Int], arrayB: Array[Int], originalArrayB: Array[Int], moneySpent: Int = -1): Int = {
      if (arrayA.isEmpty) moneySpent
      else if (arrayB.isEmpty) go(arrayA.tail, originalArrayB, originalArrayB, moneySpent)
      else if (arrayA.head + arrayB.head <= b && arrayA.head + arrayB.head > moneySpent) arrayA.head + arrayB.head
      else go(arrayA, arrayB.tail, originalArrayB, moneySpent)
    }

    max(go(keyboards, drives, drives), go(drives, keyboards, keyboards))
  }
   */

  def getMoneySpent(keyboards: Array[Int], drives: Array[Int], b: Int): Int = {
    /*
     * Write your code here.
     */
    def go(goKeyboards: Array[Int], goDrives: Array[Int], moneySpent: Int = -1): Int = {
      if (goKeyboards.isEmpty) moneySpent
      else if (goDrives.isEmpty) go(goKeyboards.tail, drives, moneySpent)
      else if (goKeyboards.head + goDrives.head == b) goKeyboards.head + goDrives.head
      else if (goKeyboards.head + goDrives.head <= b && goKeyboards.head + goDrives.head > moneySpent) go(goKeyboards, goDrives.tail, goKeyboards.head + goDrives.head)
      else go(goKeyboards, goDrives.tail, moneySpent)
    }

    go(keyboards, drives)
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val bnm = stdin.readLine.split(" ")

    val b = bnm(0).trim.toInt

    val n = bnm(1).trim.toInt

    val m = bnm(2).trim.toInt

    val keyboards = stdin.readLine.split(" ").map(_.trim.toInt)

    val drives = stdin.readLine.split(" ").map(_.trim.toInt)
    /*
     * The maximum amount of money she can spend on a keyboard and USB drive, or -1 if she can't purchase both items
     */

    implicit def reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)

    val moneySpent = getMoneySpent(keyboards.sorted, drives.sorted, b)

    println(moneySpent)

    //printWriter.close()
  }
}

