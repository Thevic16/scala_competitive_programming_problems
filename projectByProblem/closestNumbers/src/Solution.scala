import scala.io._
import scala.math._


// https://www.hackerrank.com/challenges/closest-numbers/problem

object Result {

  /*
   * Complete the 'closestNumbers' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */

//  def getSmallerDifference(arr: Array[Int]): Int = {
//    if(arr.length == 1){
//      Int.MaxValue
//    }
//    else {
//      min(arr.tail.head - arr.head, getSmallerDifference(arr.tail))
//    }
//  }
//
//  def getArrayPairSmallerDifference(smallerDifference: Int, arr: Array[Int],
//    acc: Array[Int] = Array()): Array[Int] = {
//    if(arr.length == 1) {
//       acc
//    }
//    else if(arr.tail.head - arr.head == smallerDifference) {
//      getArrayPairSmallerDifference(smallerDifference, arr.tail, acc :+ arr.head :+ arr.tail.head)
//    }
//    else {
//      getArrayPairSmallerDifference(smallerDifference, arr.tail, acc)
//    }
//  }

    def getSmallerDifferenceAndMapPair(arr: Array[Int], minDif: Int = Int.MaxValue,
      mapPair: Map[Int, Array[Int]] = Map[Int, Array[Int]]().withDefaultValue(Array())): (Int, Map[Int, Array[Int]]) = {
      if(arr.length == 1){
        (minDif, mapPair)
      }
      else if(arr.tail.head - arr.head <= minDif){
        val newMinDif: Int = arr.tail.head - arr.head
        val newArray: Array[Int] = mapPair(newMinDif) :+ arr.head :+ arr.tail.head
        val newMapPair: Map[Int, Array[Int]] = mapPair + (newMinDif -> newArray)

        getSmallerDifferenceAndMapPair(arr.tail, newMinDif, newMapPair)
      }
      else {
        getSmallerDifferenceAndMapPair(arr.tail, minDif, mapPair)
      }
    }

  def closestNumbers(arr: Array[Int]): Array[Int] = {
    // Write your code here
    val sortArr: Array[Int] = arr.sorted
//    val smallerDifference: Int = getSmallerDifference(sortArr)
//    getArrayPairSmallerDifference(smallerDifference, sortArr)

    val (minDif, mapPair) = getSmallerDifferenceAndMapPair(sortArr)

    mapPair(minDif)
  }

}

object Solution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = StdIn.readLine.trim.toInt

    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val result = Result.closestNumbers(arr)


    println(result.mkString(" "))
    //printWriter.println(result.mkString(" "))

    //printWriter.close()
  }
}
