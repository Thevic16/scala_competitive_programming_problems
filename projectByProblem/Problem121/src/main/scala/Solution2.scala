object Solution2 {
  def maxProfit(prices: Array[Int]): Int = {
    def dp(prices: Array[Int], minBuy: Int, profit: Int = 0): Int = {
      if(prices.isEmpty){
        0
      }
      else if (prices.length == 1) {
        math.max(prices.head - minBuy, profit)
      }
      else if (prices.head < minBuy) {
        dp(prices.tail, prices.head, math.max(prices.head - minBuy, profit))
      }
      else {
        dp(prices.tail, minBuy, math.max(prices.head - minBuy, profit))
      }
    }

    dp(prices.tail, prices.head)
  }
}

object Test2 extends App {
  val solution = Solution2

  println(solution.maxProfit(Array(1, 2)))
}