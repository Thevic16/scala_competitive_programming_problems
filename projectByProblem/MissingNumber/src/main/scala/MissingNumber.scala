import scala.io._
object MissingNumber {
  def main(args: Array[String]): Unit = {
    val n: Int = StdIn.readLine.trim.toInt
    val as: List[Int] = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt).toList


    def solution(n: Int, as: List[Int], i: Int = 1): Int = {
      if (i > n) -1
      else if (i != as.head) i
      else solution(n, as.tail, i + 1)
    }

    println(solution(n, as.sorted))
  }
}
