
object Solution3 {
  def minCostClimbingStairs(cost: Array[Int]): Int = {
    def go(arrayCost: Array[Int], i: Int = 0,  myCost: Int = 0): Int = {
      if (arrayCost.isEmpty) myCost
      else {
        if(arrayCost.length >= 2) {
          math.min(go(arrayCost.tail, i + 1, myCost + arrayCost.head), go(arrayCost.tail.tail, i + 2, myCost + arrayCost.tail.head))
        }
        else {
          go(arrayCost.tail, i + 1, myCost + arrayCost.head)
        }
      }
    }

    go(cost :+ 0)
  }

  println(minCostClimbingStairs(Array(10,15,20)))
}
