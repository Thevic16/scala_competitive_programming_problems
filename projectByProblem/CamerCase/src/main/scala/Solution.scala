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
   * Complete the 'camelcase' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts STRING s as parameter.
   */

  def camelcase(s: String): Int = {
    // Write your code here
    def go(s: String, cont: Int = 0): Int = {
      if(s.isEmpty) cont
      else if(s.head.isUpper) go(s.tail, cont + 1)
      else go(s.tail, cont)
    }

    go(s)
  }

}

object Solution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val s = StdIn.readLine

    val result = Result.camelcase(s)

    println(result)

    //printWriter.close()
  }
}
