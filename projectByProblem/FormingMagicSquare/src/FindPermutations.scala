import scala.annotation.tailrec

// https://www.hackerrank.com/challenges/magic-square-forming/problem
object FindPermutations extends App {
  import FindPermutationUtility._

  private val allPossiblesSquares: Array[Array[Int]] = getAllPosiblesSquares()
  //printSquares(allPossiblesSquares)
  private val allPossiblesMagicSquares: Array[Array[Int]] = allPossiblesSquares.filter(isMagicSquare)

  printSquares(allPossiblesMagicSquares)
}

object FindPermutationUtility {
  trait Diagonal

  case object D1 extends Diagonal

  case object D2 extends Diagonal

  val allPossiblesNumbers: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val numberOfPermutationsForNineElements: Int = 362880

  def getAllPosiblesSquares(allPossiblesSquares: Array[Array[Int]] = Array(Array())): Array[Array[Int]] = {
    if (allPossiblesSquares.length == numberOfPermutationsForNineElements
      && allPossiblesSquares.last.length == 9) allPossiblesSquares
    else getAllPosiblesSquares(allPossiblesSquares.flatMap((square: Array[Int]) =>
      addElementToPossiblesSquares(allPossiblesNumbers, square)))
  }

  def rotate(allPossiblesNumbers: Array[Int], numberOfRotations: Int): Array[Int] = {
    if (numberOfRotations <= 0) allPossiblesNumbers
    else rotate(allPossiblesNumbers.tail :+ allPossiblesNumbers.head, numberOfRotations - 1)
  }

  def addElementToPossiblesSquares(allPossiblesNumbers: Array[Int], square: Array[Int]): Array[Array[Int]] = {

    def go(filterPossiblesNumbers: Array[Int], newPossiblesSquares: Array[Array[Int]] = Array())
    : Array[Array[Int]] = {
      if (filterPossiblesNumbers.isEmpty) newPossiblesSquares
      else go(filterPossiblesNumbers.tail, newPossiblesSquares :+ (square :+ filterPossiblesNumbers.head))
    }

    go(allPossiblesNumbers.filter(square.contains(_) == false))
  }

  // verify methods
  def verifyRow(s: Array[Int], row: Int, objective: Int = 15): Boolean = {
    s.slice(row * 3, row * 3 + 3).sum == objective
  }

  def verifyColumn(s: Array[Int], column: Int, objective: Int = 15): Boolean = {

    @tailrec
    def getArrayColumn(column: Int, acc: Array[Int] = Array()): Array[Int] = {
      if(column >= s.length) acc
      else getArrayColumn(column + 3, acc :+ s(column))
    }

    getArrayColumn(column).sum == objective
  }

  def verifyDiagonal(s: Array[Int], diagonal: Diagonal, objective: Int = 15): Boolean = {
    diagonal match {
      case D1 => s(0) + s(4) + s(8) == objective
      case D2 => s(2) + s(4) + s(6) == objective
    }
  }

  def isMagicSquare(s: Array[Int]): Boolean = {
    verifyRow(s, 0) && verifyRow(s, 1) && verifyRow(s, 2) && verifyColumn(s, 0) &&
      verifyColumn(s, 1) && verifyColumn(s, 2) && verifyDiagonal(s, diagonal = D1) &&
      verifyDiagonal(s, diagonal = D2)
  }

  @tailrec
  def printSquares(squares: Array[Array[Int]], ind: Int = 1): Unit = {
    if(squares.isEmpty) ()
    else {
      print(s"#$ind: ")
      squares.head.foreach(print)
      println()
      printSquares(squares.tail, ind+1)
    }
  }

}