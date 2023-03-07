import scala.annotation.tailrec

object Solution {

  def tribonacci(n: Int): Int = {
    @tailrec
    def go(n: Int, sequences: List[Int] = List(0, 1, 1)): Int = {
      if(sequences.isDefinedAt(n)) {
        sequences(n)
      }
      else {
         go(n, sequences :+ generateNewTribonacci(sequences))
      }
    }

    go(n)
  }

  def generateNewTribonacci(sequences: List[Int]): Int = {
    sequences.reverse.take(3).sum
  }
}
