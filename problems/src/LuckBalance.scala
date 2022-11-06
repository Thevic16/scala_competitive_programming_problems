
import java.util._
import scala.collection.immutable._
import scala.io._


// https://www.hackerrank.com/contests/final-contest-29072022/challenges/luck-balance

object LuckBalanceResult {

  /*
   * Complete the 'luckBalance' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. 2D_INTEGER_ARRAY contests
   */


  def luckBalance(k: Int, contests: Array[Array[Int]]): Int = {
    // Write your code here
    val contestGroupBy = contests.groupBy(contest => contest(1) == 1)
    val contestGroupByImportant: Option[Array[Array[Int]]] = contestGroupBy.get(true).map(l => l.sortBy(_(0)).reverse)
    val contestGroupByNoImportant: Option[Array[Array[Int]]] = contestGroupBy.get(false)

    val importantContestToLost: Option[Array[Array[Int]]] = contestGroupByImportant.map(l => l.take(k))
    val contestsToLost: Option[Array[Array[Int]]] = Option(contestGroupByNoImportant.getOrElse(Array[Array[Int]]()) ++
      importantContestToLost.getOrElse(Array[Array[Int]]()))

    val contestToWin: Option[Array[Array[Int]]] = for {
      arrImportContests <- contestGroupByImportant
      arrImportContestsToLost <- importantContestToLost
    } yield arrImportContests.filter(contest => !arrImportContestsToLost.contains(contest))

    val sumLuckOnLost: Option[Int] = contestsToLost.map(_.foldLeft(0)((sumLuck:Int, contest:Array[Int]) => sumLuck + contest(0)))
    val sumLuckOnWin: Option[Int] = contestToWin.map(_.foldLeft(0)((sumLuck:Int, contest:Array[Int]) => sumLuck + contest(0)))

    sumLuckOnLost.getOrElse(0) - sumLuckOnWin.getOrElse(0)
  }


}

object LuckBalanceSolution {
  def main(args: Array[String]) {
    val firstMultipleInput = StdIn.readLine.replaceAll("\\s+$", "").split(" ")

    val n = firstMultipleInput(0).toInt

    val k = firstMultipleInput(1).toInt

    val contests = Array.ofDim[Int](n, 2)

    for (i <- 0 until n) {
      contests(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }

    val result = LuckBalanceResult.luckBalance(k, contests)

    println(result)

    //test
    /*
    val contestGroupBy = contests.groupBy(contest => contest(1) == 1)
    val contestGroupByTrue = contestGroupBy(true)
    val contestGroupByFalse = contestGroupBy(false)
    println(contestGroupByTrue.mkString("Array(", ", ", ")"))
    println(contestGroupByFalse.mkString("Array(", ", ", ")"))
     */
  }
}
