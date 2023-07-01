import scala.io.StdIn
import scala.math.abs

// https://codeforces.com/problemset/problem/1409/A
object Solution extends App{
  var t = StdIn.readLine.trim.toInt

  def solution(diff: Int, k: Int = 10, cont: Int = 0): Int = {
    if(diff == 0){
      cont
    }
    else if(k <= diff){
      solution(diff - k * (diff / k), k, cont + (k * (diff / k))/k)
    }
    else {
      solution(diff, k-1, cont)
    }
  }

  while(t > 0){
    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    println(solution(abs(arr(0) - arr(1))))
    t = t - 1
  }

}
