import scala.annotation.tailrec
import scala.collection.mutable
import scala.io._

object Main extends App {
  type Cont[Hole,Result] = Hole => Result

  val n: Int = StdIn.readLine.trim.toInt
  val dices: List[Int] = List(1, 2, 3, 4, 5, 6)
  var memo: mutable.Map[Int, BigInt] = mutable.Map()

  def dp(n: Int, dices: List[Int], sum: Int = 0): BigInt = {
    if (sum == n) {
      1L
    }
    else if (sum > n) {
      0L
    }
    else if (memo.contains(sum)){
      memo(sum)
    }
    else {
      val result: BigInt = dices.foldLeft(BigInt(0))((acc: BigInt, dice: Int) => acc + dp(n, dices, sum + dice))
      memo.update(sum, result)
      result
    }
  }

  /*
  Idea cambiar el formato de mi funcion dp para que acumule los resultados de las sumas en una lista
  en vez de en los parametros de la funcion, el resultado seria una lista de sumas entonces las sumas
  que no sean igual a n se descartan.

  O tal vez no puede ser que halla una manera de hacerlo asi.
  */
//  def dp[Result](n: Int, dices: List[Int], sum: Int = 0)(k: Cont[Int, Result]): Result = {
//    if (sum == n) {
//      1L
//    }
//    else if (sum > n) {
//      0L
//    }
//    else if (memo.contains(sum)) {
//      memo(sum)
//    }
//    else {
//      val result: BigInt =
//      memo.update(sum, result)
//      result
//    }
//  }

  //println(dp(n, dices) % (10^9 + 7))
  println(dp(n, dices))
}




