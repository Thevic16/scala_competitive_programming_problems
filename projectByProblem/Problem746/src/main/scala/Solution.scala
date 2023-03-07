import scala.annotation.tailrec
// https://leetcode.com/problems/min-cost-climbing-stairs/

object Solution {
  case class Step(number: Int, indexCostList: List[(Int, Int)])

  def minCostClimbingStairs(cost: Array[Int]): Int = {
      val n: Int = cost.length

    @tailrec
    def go(step: Step = Step(1, List((0, cost(0)), (1, cost(1)))) ): Int = {
      if (step.indexCostList.forall(indexCost => indexCost._1 == n)){
        step.indexCostList.map(_._2).min
      }
      else {
        go(generateNextStep(cost :+ 0, step))
      }
    }

    go()
  }

  def generateNextStep(arrayCost: Array[Int], step: Step): Step = {
    Step(step.number+1,
      step.indexCostList.flatMap{ case (i, cost) =>
        val cost1: Option[Int] = getCostByIndex(arrayCost, i + 1)
        val cost2: Option[Int] = getCostByIndex(arrayCost, i + 2)

        (cost1, cost2) match {
          case (Some(cost1), Some(cost2)) =>
            List((i + 1, cost + cost1),
              (i + 2, cost + cost2))
          case (Some(cost1), None) =>
            List((i + 1, cost + cost1))
          case _ => List((i, cost))
        }
      })
  }

  def getCostByIndex(arrayCost: Array[Int], i: Int): Option[Int] = {
    if(arrayCost.isDefinedAt(i)){
      Some(arrayCost(i))
    }
    else {
      None
    }
  }

}
