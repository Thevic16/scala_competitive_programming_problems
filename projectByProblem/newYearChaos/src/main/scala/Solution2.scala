
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._


// https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

object Result2 {

  /*
   * Complete the 'minimumBribes' function below.
   *
   * The function accepts INTEGER_ARRAY q as parameter.
   */

  def swapIndexes(i: Int, j: Int, original: Array[Int]): Array[Int]  = {
    val temp: Int = original(i)
    original(i) = original(j)
    original(j) = temp

    original
  }

  def minimumBribes(q: Array[Int]): Any = {
    // Write your code here
    val n: Int = q.size;

    def go(q: Array[Int], original: Array[Int] = (1 to n).toArray, i: Int = n-1, bribes: Int = 0, limit: Int = 0): Either[String, Int] = {
      if(i < 0){
        Right(bribes)
      }
      else if(limit > 2){
        Left("Too chaotic")
      }
      else if(q(i) == original(i)){
        go(q, original, i-1, bribes, 0)
      }
      else {
        go(q, swapIndexes(i, i-1, original), i-1, bribes+1, limit+1)
      }
    }

    go(q).getOrElse("Too chaotic")
  }

}

object Solution2 {
  def main(args: Array[String]) {
    val t = StdIn.readLine.trim.toInt

    for (tItr <- 1 to t) {
      val n = StdIn.readLine.trim.toInt

      val q = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
      println(Result2.minimumBribes(q))
    }
  }
}
