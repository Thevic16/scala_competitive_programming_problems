import scala.io.StdIn
import scala.math.abs

// https://codeforces.com/problemset/problem/1399/A
object Solution extends App {
  var t = StdIn.readLine.trim.toInt

  def solution(ref: List[Int], arr: List[Int] = List()): Int = {
    if(ref.size == 1){
      (arr :+ ref.head).size
    }
    else if(abs(ref.head - ref.tail.head) <= 1) {
      solution(ref.tail, arr)
    }
    else {
      solution(ref.tail, arr :+ ref.head)
    }
  }

  while(t > 0){
    val n = StdIn.readLine.trim.toInt
    val ref = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt).toList

    if(solution(ref.sorted) == 1){
      println("YES")
    }
    else {
      println("NO")
    }

    t -= 1
  }

}
