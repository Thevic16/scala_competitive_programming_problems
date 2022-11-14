import scala.annotation.tailrec

object PascalTriangle extends App {
  var memorization: Map[Int, List[List[Int]]] = Map(1 -> List(List(1)), 2 -> List(List(1), List(1, 1)))
  def generate(numRows: Int): List[List[Int]] = {
    if(memorization.contains(numRows)) memorization(numRows)
    else {
      val currentPascalTriangule: List[List[Int]] =
      if (memorization.contains(numRows - 1)) {
        memorization(numRows - 1) :+ getNewRow(memorization(numRows - 1).last)
      }
      else {
        val lastPascalTriangule: List[List[Int]] = generate(numRows - 1)
        lastPascalTriangule :+ getNewRow(lastPascalTriangule.last)
      }

      memorization + (numRows -> currentPascalTriangule)
      currentPascalTriangule
    }
  }

  def getNewRow(previousRow: List[Int]): List[Int]  = {
    @tailrec
    def go(remindPreviewsRow: List[Int], newRow: List[Int] = List(1)): List[Int] = {
      if(remindPreviewsRow.size == 1) newRow :+ 1
      else {
        //println(remindPreviewsRow.tail)
        go(remindPreviewsRow.tail, newRow :+ remindPreviewsRow.head + remindPreviewsRow.tail.head)
      }
    }

    go(previousRow)
  }

  println(generate(5))
}
