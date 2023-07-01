
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._


// https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

object Result {

  /*
   * Complete the 'minimumBribes' function below.
   *
   * The function accepts INTEGER_ARRAY q as parameter.
   */

  def minimumBribes(q: Array[Int]): Any = {
    // Write your code here

    def go(q: Array[Int], i: Int = 1, bribes: Int = 0): Either[String, Int] = {
      if(q.isEmpty){
        Right(bribes)
      }
      else if(q.head > i){
        if(q.head - i > 2){
          Left("Too chaotic")
        }
        else {
          go(q.tail, i+1, bribes + q.head - i)
        }
      }
      else if(q.length > 1){
        if(q.head > q.tail.head){
          go(q.tail, i+1, bribes+1)
        }
        else {
          go(q.tail, i+1, bribes)
        }
      }
      else{
        go(q.tail, i+1, bribes)
      }
    }

    go(q).getOrElse("Too chaotic")
  }

}

object Solution {
  def main(args: Array[String]) {
    val t = StdIn.readLine.trim.toInt

    for (tItr <- 1 to t) {
      val n = StdIn.readLine.trim.toInt

      val q = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
      println(Result.minimumBribes(q))
    }
  }
}
