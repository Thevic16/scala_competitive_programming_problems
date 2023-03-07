import scala.collection.mutable

object Solution2 extends App {
  var memo: mutable.Map[Int, Int] = mutable.Map()
  def minCostClimbingStairs(cost: Array[Int]): Int = {
    def go(arrayCost: Array[Int], i: Int = 0,  myCost: Int = 0): Int = {
      if (arrayCost.isEmpty) myCost
      else {
        if(arrayCost.length >= 2) {
          memo.update(i + 1, go(arrayCost.tail, i + 1, myCost + arrayCost.head))
          memo.update(i + 2, go(arrayCost.tail.tail, i + 2, myCost + arrayCost.tail.head))
          math.min(memo(i + 1), memo(i + 2))
        }
        else {
          memo.update(i + 1, go(arrayCost.tail, i + 1, myCost + arrayCost.head))
          memo(i + 1)
        }
      }
    }

    go(cost :+ 0)
  }

  println(minCostClimbingStairs(Array(10,15,20)))
}
