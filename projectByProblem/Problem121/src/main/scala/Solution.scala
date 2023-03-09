import scala.collection.mutable

object Solution {
  def maxProfit(prices: Array[Int]): Int = {
    var memo: mutable.Map[(Int, Int), Int] = mutable.Map()

//    def dp(prices: Array[Int], buy: Int = prices.length - 2, sell: Int = prices.length - 1): Int = {
//      if(buy < 0){
//        0
//      }
//      else if(memo.isDefinedAt((buy, sell))){
//        println("memo!!!:" + (buy, sell) + " " + memo((buy, sell)))
//        memo((buy, sell))
//      }
//      else{
//        println("(buy, sell):" + (buy, sell))
//        val result: Int = math.max(math.max(prices(sell) - prices(buy),
//          dp(prices, buy-1, sell)),
//          dp(prices, buy-1, sell-1))
//
//        memo.update((buy, sell), result)
//        memo((buy, sell))
//      }
//    }

    def dp(prices: Array[Int], minBuy: Int): Int = {
      if(prices.length == 1){
        0
      }
      else if(prices.head < minBuy){
        math.max(prices.head - minBuy, dp(prices.tail, prices.head))
      }
      else {
        math.max(prices.head - minBuy, dp(prices.tail, minBuy))
      }
    }

    dp(prices.tail, prices.head)
  }
}

object Test extends App {
  val solution = Solution

  println(solution.maxProfit(Array(7,6,4,3,1)))
}