//import scala.io.StdIn
//
//object Result3 {
//
//  /*
//   * Complete the 'minimumBribes' function below.
//   *
//   * The function accepts INTEGER_ARRAY q as parameter.
//   */
//
//  case class Person(number: Int, peopleAhead: Int)
//
//  def minimumBribes(q: Array[Int]): Any = {
//    // Write your code here
//    val n: Int = q.size
//    var peopleNormalOrder: Array[Person] = (1 to n).map(i => Person(i, n-i)).toArray
//  }
//
//  def getPeopleRealOrder(q: Array[Int], i : Int = 1, peopleNormalOrder: Array[Person]): Array[Person] = {
//
//  }
//}
//
//object Solution3 {
//  def main(args: Array[String]) {
//    val t = StdIn.readLine.trim.toInt
//
//    for (tItr <- 1 to t) {
//      val n = StdIn.readLine.trim.toInt
//
//      val q = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
//      println(Result3.minimumBribes(q))
//    }
//  }
//}
