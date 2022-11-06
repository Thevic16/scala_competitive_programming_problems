import scala.collection.mutable
import scala.io.StdIn
// Ejercicio de competencia 2021

/*

input 1:
2 3
1 9 3
2 8 9

input 2:
2 5
1 2 3 4 5
3 1 4 1 5

input 3:
1 2
2 6

input 4:
2 5
1 3 3 4 5
3 1 4 1 5

Page to generate matrices
https://es.planetcalc.com/9081/

*/

object MatrizParidad extends App{
  import MatrizParidadUtility._

  val rc = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt).toList
  val r = rc(0)
  val c = rc(1)

  val matrix: List[List[Int]] = getMatrix(r)


  val solutionMatrix = solution(matrix, r, c)
  // printMatrix(solutionMatrix, r, c)
  printStatistic(solutionMatrix, r, c)
}

object MatrizParidadUtility {

  def solution(matrix: List[List[Int]], r: Int, c: Int): List[List[Int]] = {

    def go(matrix: List[List[Int]]): List[List[Int]] = {
      val rowBooleanMatrix = getRowBooleanMatrix(matrix, c)
      val columnBooleanMatrix = getColumnBooleanMatrix(matrix, r, c)
      val combineMatrix = combineBooleanMatrices(rowBooleanMatrix, columnBooleanMatrix)

      if(!isAllTrue(combineMatrix)){
        val newMatrix: List[List[Int]] = addOneToFalse(matrix, combineMatrix, r, c)
        solution(newMatrix, r, c)
      }
      else if(!isAllTrue(columnBooleanMatrix)){
        val newMatrix: List[List[Int]] = addOneToFalse(matrix, columnBooleanMatrix, r, c)
        solution(newMatrix, r, c)
      }
      else if (!isAllTrue(rowBooleanMatrix)) {
        val newMatrix: List[List[Int]] = addOneToFalse(matrix, rowBooleanMatrix, r, c)
        solution(newMatrix, r, c)
      }
      else {
        matrix
      }
    }

    go(matrix)
  }

  def getMatrix(r: Int, i: Int = 0, matrix: List[List[Int]] = List()): List[List[Int]] = {
    if (i == r) matrix
    else {
      val newList: List[Int] = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt).toList
      val newMatrix: List[List[Int]] = matrix :+ newList
      getMatrix(r, i + 1, newMatrix)
    }
  }

  def getRowBooleanMatrix(matrix: List[List[Int]], c: Int): List[List[Boolean]] = {

    def go(matrix: List[List[Int]], rowBooleanMatrix: List[List[Boolean]] = List()): List[List[Boolean]] = {
      if (matrix.isEmpty) rowBooleanMatrix
      else if (matrix.head.sum % 2 == 0)  go(matrix.tail, rowBooleanMatrix :+ getRowBooleanList(c, value = true))
      else go(matrix.tail, rowBooleanMatrix :+ getRowBooleanList(c, value = false))
    }

    go(matrix)
  }


  def combineBooleanMatrices(matrix1: List[List[Boolean]], matrix2: List[List[Boolean]]): List[List[Boolean]] = {

    def go(matrix1: List[List[Boolean]], matrix2: List[List[Boolean]],
           accMatrix: List[List[Boolean]] = List()): List[List[Boolean]] = {
      if(matrix1.isEmpty) accMatrix
      else {
        val combineBooleanList: List[Boolean] = getCombineBooleanList(matrix1.head, matrix2.head)
        go(matrix1.tail, matrix2.tail, accMatrix :+ combineBooleanList)
      }
    }

    go(matrix1, matrix2)
  }

  // Methods auxiliary
  def getRowBooleanList(c: Int, value: Boolean, acc: List[Boolean] = List()): List[Boolean] = {
    if (c <= 0) acc
    else getRowBooleanList(c - 1, value, acc :+ value)
  }

  def getColumnBooleanMatrix(matrix: List[List[Int]], r: Int, c: Int): List[List[Boolean]] = {
    val columnBooleanList = getColumnBooleanList(matrix, c)
    generateColumnBooleanList(r, columnBooleanList)
  }

  def generateColumnBooleanList(r: Int, value: List[Boolean], acc: List[List[Boolean]] = List()): List[List[Boolean]] = {
    if (r <= 0) acc
    else generateColumnBooleanList(r - 1, value, acc :+ value)
  }

  def getColumnBooleanList(matrix: List[List[Int]], c: Int, i: Int = 0, acc: List[Boolean] = List()): List[Boolean] = {
    def go(i: Int = 0, acc: List[Boolean] = List()): List[Boolean] = {
      if (i >= c) acc
      else {
        val columnBoolean: Boolean = matrix.map(list => list(i)).sum % 2 == 0
        go(i + 1, acc :+ columnBoolean)
      }
    }

    go()
  }

  def getCombineBooleanList(list1: List[Boolean], list2: List[Boolean]): List[Boolean] = {
    def go(list1: List[Boolean], list2: List[Boolean], acc: List[Boolean] = List()): List[Boolean] = {
      if (list1.isEmpty) acc
      else {
        val newValue = list1.head || list2.head
        go(list1.tail, list2.tail, acc :+ newValue)
      }
    }

    go(list1, list2)
  }

  // Method print
  def printMatrix(matrix: List[List[Any]], r: Int, c: Int): Unit = {
    for (i <- 0.until(r)) {
      for (j <- 0.until(c)) {
        if(matrix(i)(j) == true)
          print(matrix(i)(j) + "  ")
        else
          print(matrix(i)(j) + " ")
      }
      print("\n")
    }
  }

  def printStatistic(matrix: List[List[Int]], r: Int, c: Int): Unit = {
    println("Matrix:")
    println(r + " " + c)
    printMatrix(matrix, r, c)
    //println(matrix)
    println()

    println("Row boolean matrix:")
    val rowBooleanMatrix = getRowBooleanMatrix(matrix, c)
    printMatrix(rowBooleanMatrix, r, c)
    //println(rowBooleanMatrix)
    println()

    println("Column boolean matrix:")
    val columnBooleanMatrix = getColumnBooleanMatrix(matrix, r, c)
    printMatrix(columnBooleanMatrix, r, c)
    //println(columnBooleanMatrix)
    println()

    println("Combine the two matrix with a OR:")
    val combineMatrix = combineBooleanMatrices(rowBooleanMatrix, columnBooleanMatrix)
    printMatrix(combineMatrix, r, c)
    //println(combineMatrix)
  }

  // Methods verify results
  def isAllTrue(combinationMatrix: List[List[Boolean]]): Boolean = {

    def go(combinationMatrix: List[List[Boolean]]): Boolean = {
      if (combinationMatrix.isEmpty) true
      else if (!combinationMatrix.head.fold(true)(_ && _)) false
      else go(combinationMatrix.tail)
    }

    go(combinationMatrix)
  }

  def addOneToFalse(originalMatrix: List[List[Int]], combinationMatrix: List[List[Boolean]], r: Int, c: Int): List[List[Int]] = {

    def go(matrix: List[List[Int]], combinationMatrix: List[List[Boolean]], i: Int = 0, j: Int = 0): List[List[Int]] = {
      if(j >= c) originalMatrix
      else if(i >= r) go(matrix, combinationMatrix, 0, j+1)
      else if(!combinationMatrix(i)(j)) getMatrixAddOneByLocation(originalMatrix, i, j)
      else go(matrix, combinationMatrix, i+1, j)
    }

    go(originalMatrix, combinationMatrix)
  }

  def getMatrixAddOneByLocation(originalMatrix: List[List[Int]], i: Int, j: Int): List[List[Int]] = {
    var arrayOfArray: Array[Array[Int]] = originalMatrix.toArray.map(_.toArray)
    arrayOfArray(i)(j) = arrayOfArray(i)(j)+1
    arrayOfArray.toList.map(_.toList)
  }

}
