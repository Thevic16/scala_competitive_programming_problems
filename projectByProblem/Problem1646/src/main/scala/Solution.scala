import scala.collection.mutable
// https://leetcode.com/problems/get-maximum-in-generated-array/
object Solution {
  def getMaximumGenerated(n: Int): Int = {
    var memo: mutable.Map[Int, Int] = mutable.Map(0 -> 0, 1 -> 1)

    def dp(i: Int): Int = {
      if(memo.isDefinedAt(i)){
        memo(i)
      }
      else if(i % 2 == 0) {
        memo.update(i, dp(i/2))
        memo(i)
      }
      else {
        memo.update(i, dp(i/2) + dp(i/2 + 1))
        memo(i)
      }
    }

    def go(n: Int): Unit = {
      if(n <= 1){
        ()
      }
      else {
        dp(n)
        go(n-1)
      }
    }

    if(n == 0){
      memo(n)
    }
    else {
      go(n)
      memo.values.max
    }
  }
}

object Test extends App {
  val solution = Solution

  println(solution.getMaximumGenerated(4))
}
