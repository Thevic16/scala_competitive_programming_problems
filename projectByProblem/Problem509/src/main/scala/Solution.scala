import scala.collection.mutable

// https://leetcode.com/problems/fibonacci-number/
object Solution {
  def fib(n: Int): Int = {
    var memo: mutable.Map[Int, Int] = mutable.Map(0->0, 1->1)

    def dp(n: Int): Int = {
      if (memo.isDefinedAt(n)){
        memo(n)
      }
      else {
        memo.update(n,  dp(n-1) + dp(n-2))
        memo(n)
      }
    }

    dp(n)
  }
}
