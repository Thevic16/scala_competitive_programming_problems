

object DivisorGame extends App {
  sealed trait Player
  case object ALice extends Player
  case object Bob extends Player

  //var memorization: Map[Int, List[Int]] = Map(1 -> List(1))

  def divisorGame(n: Int): Boolean = {
    def go(n: Int, player: Player, winner: Boolean = false): Boolean = {
      if (n == 1) player match {
        case ALice => false
        case Bob => true
      }
      else {
        val optimalDivisor: Int = getDivisors(n).head
        val otherPlayer: Player = player match {
          case ALice => Bob
          case Bob => ALice
        }
        winner || go(n - optimalDivisor, otherPlayer)
      }
    }

    go(n, ALice)
  }

  def getDivisors(n: Int): List[Int] = {

    def go(n: Int, i: Int = 1, divisors: List[(Int, Boolean)] = List()): List[Int] = {
      if (i >= n) divisors.sortWith(getDivisorValue(_) > getDivisorValue(_)).map(_._1)
      else if (n % i == 0) go(n, i + 1, divisors :+ getDivisorTuple(n, i))
      else go(n, i + 1, divisors)
    }

    go(n)
  }

  def getDivisorTuple(n: Int, i: Int): (Int, Boolean) = {
    if((n - i) % 2 != 0) (i, true)
    else (i, false)
  }

  def getDivisorValue(divisorTuple: (Int, Boolean)): Int = {
    if(divisorTuple._2) divisorTuple._1 + 1000
    else divisorTuple._1
  }

  println(divisorGame(2))
  println(divisorGame(3))
  //println(divisorGame(5))

}
