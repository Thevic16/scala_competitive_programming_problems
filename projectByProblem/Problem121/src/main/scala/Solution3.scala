object Solution3 {
  def maxProfit(prices: Array[Int]): Int = {
    val n = prices.length
    def dp(minBuy: Int, i: Int = 1, profit: Int = 0): Int = {
      if(i >= n){
        0
      }
      else if (i == n-1) {
        math.max(prices(i) - minBuy, profit)
      }
      else if (prices(i) < minBuy) {
        dp(prices(i), i + 1, math.max(prices(i) - minBuy, profit))
      }
      else {
        dp(minBuy, i + 1, math.max(prices(i) - minBuy, profit))
      }
    }

    dp(prices.head)
  }
}

object Test3 extends App {
  val solution = Solution3

  println(solution.maxProfit(Array(7,1,5,3,6,4)))
}