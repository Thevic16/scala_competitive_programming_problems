import scala.collection.mutable

object Solution {
  def getRow(rowIndex: Int): List[Int] = {
    var memo: mutable.Map[Int, List[Int]] = mutable.Map(0 -> List(1), 1 -> List(1, 1))

    def dp(rowIndex: Int): List[Int] = {
      if(memo.isDefinedAt(rowIndex)){
        memo(rowIndex)
      }
      else {
        memo.update(memo.last._1+1, generateNewRow(memo.last._2))
        dp(rowIndex)
      }
    }

    dp(rowIndex)
  }

  def generateNewRow(lastRow: List[Int], result: List[Int] = List(1)): List[Int] = {
    if(lastRow.length == 1){
      result :+ 1
    }
    else {
      generateNewRow(lastRow.tail, result :+ lastRow.head + lastRow.tail.head)
    }
  }

}
