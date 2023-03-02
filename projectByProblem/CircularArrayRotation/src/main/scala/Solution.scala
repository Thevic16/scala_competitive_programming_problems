import scala.collection.immutable._
import scala.io._


// https://www.geeksforgeeks.org/queue-in-scala/


object Result {

  /*
   * Complete the 'circularArrayRotation' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY a
   *  2. INTEGER k
   *  3. INTEGER_ARRAY queries
   */

  //https://www.hackerrank.com/challenges/circular-array-rotation/problem?isFullScreen=false
  def circularArrayRotation(a: Array[Int], k: Int, queries: Array[Int]): Array[Int] = {
    // Write your code here
    val aReverse: Array[Int] = a.reverse
    val queue: scala.collection.immutable.Queue[Int] = aReverse.foldLeft(Queue.empty[Int])((q, n) => q.enqueue(n))
    val aRotated: Array[Int] = executeCircularRotation(queue, k)
    val n: Int = a.length - 1

    queries.map(q => aRotated(n-q))
  }

  def executeCircularRotation(a: scala.collection.immutable.Queue[Int], k: Int): Array[Int] = {
    if(k == 0) a.toArray
    else executeCircularRotation(a.tail :+ a.head, k-1)
  }

  val prueba : scala.collection.immutable.Queue[Int] = Queue(1, 2 ,3 , 4)


}

object Solution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val firstMultipleInput = StdIn.readLine.replaceAll("\\s+$", "").split(" ")

    val n = firstMultipleInput(0).toInt

    val k = firstMultipleInput(1).toInt

    val q = firstMultipleInput(2).toInt

    val a = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val queries = Array.ofDim[Int](q)

    for (i <- 0 until q) {
      val queriesItem = StdIn.readLine.trim.toInt
      queries(i) = queriesItem
    }

    val result = Result.circularArrayRotation(a, k, queries)

    println(result.mkString("\n"))

    //printWriter.close()
  }
}
