import scala.collection.mutable

object Solution {
  def climbStairs(n: Int): Int = {
    var memo: mutable.Map[Int, Int] = mutable.Map()

    def dp(n: Int): Int = {
      if(n <= 1) {
        1
      }
      else if(memo.isDefinedAt(n)){
        memo(n)
      }
      else {
        val result: Int = dp(n-1) + dp(n-2)
        memo.update(n, result)
        result
      }
    }
    dp(n)
  }
}

object Result extends App {
  val solution = Solution

  println(solution.climbStairs(45))
}
