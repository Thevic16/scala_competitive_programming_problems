// https://www.hackerrank.com/challenges/drawing-book/problem?isFullScreen=false

import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._


object DrawingBookResult {

  /*
   * Complete the 'pageCount' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER p
   */

  def generateBook(n: Int, list: List[Int] = List(0)): List[Int] = {
    if(n == 0 || n <0) list
    else generateBook(n-1,  (list.head + 1) :: list)
  }

  def getBook(n: Int): List[Int] = {
    val book: List[Int] = generateBook(n)

    if (book.size % 2 != 0) 0 +: book
    else book
  }

  def findPage(p: Int, book: List[Int], pageCount:Int = 0): Int = {
    if(book.take(2).contains(p)) pageCount
    else findPage(p, book.tail.tail, pageCount+1)
  }

  def pageCount(n: Int, p: Int): Int = {
    // Write your code here
    val reverseBook: List[Int] = getBook(n)
    val book: List[Int] = reverseBook.reverse

    min(findPage(p,reverseBook), findPage(p,book))
  }

}

object DrawingBookSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = StdIn.readLine.trim.toInt

    val p = StdIn.readLine.trim.toInt

    val result = DrawingBookResult.pageCount(n, p)

    println(result)

    //printWriter.close()
  }
}

