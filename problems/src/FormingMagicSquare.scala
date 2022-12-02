//import scala.annotation.tailrec
//import scala.io._
//
//
//object FormingMagicSquareResult {
//
//  /*
//   * Complete the 'formingMagicSquare' function below.
//   *
//   * The function is expected to return an INTEGER.
//   * The function accepts 2D_INTEGER_ARRAY s as parameter.
//   */
//
//  trait Diagonal
//  case object D1 extends Diagonal
//  case object D2 extends Diagonal
//
//  case class InUseNumber(number: Int, InUse: Boolean, repeated: Boolean)
//
//  def formingMagicSquare(s: Array[Array[Int]]): Int = {
//    // Write your code here
//    val listInUseNumbers: List[InUseNumber] = addTheRestOfTheNumbers(getSetInUseNumbers(s.flatMap(_.toArray)))
//    5
//  }
//
//  /*
//  * Tengo que pensar en otra solucion, porque solo reemplazar para que sean diferentes no va a ser la solucion
//  * Oh
//  * Pudiera primero reemplazar para que sean diferentes
//  * Y despues hacer otra funcion recursiva que trate de organizar para que todo quede en 15
//  * Y despues hace una funcion que resiva la matriz original y la nueva y calcule el costo.
//  *
//  * La idea mas clara seria un algoritmo recursivo que haga que todo sean diferentes (primero(reemplazar los
//  * repetidos por los que no aparecen de todas las formas posibles)) y que los organize (busque reemplazar dos
//  *  numeros a la vez para ver que cumpla con magic square) para que sea un magic square entonces considerar
//  *  el costo (la funcion que mencione anteriormente)
//  *  para saber que solution tiene el menor costo.
//  * */
////  def solution(s: Array[Int], listInUseNumbers: List[InUseNumber], cost: Int = 0): Array[Int] = {
////
////  }
//
//  @tailrec
//  def getSetInUseNumbers(s: Array[Int],
//    listInUseNumbers: List[InUseNumber]  = List()): List[InUseNumber] = {
//    if(s.isEmpty) listInUseNumbers
//    else if(listInUseNumbers.map(_.number).contains(s.head)) getSetInUseNumbers(s.tail, listInUseNumbers)
//    else {
//      if(s.tail.contains(s.head)){
//        getSetInUseNumbers(s.tail, listInUseNumbers :+ InUseNumber(s.head, InUse = true, repeated = true))
//      }
//      else {
//        getSetInUseNumbers(s.tail, listInUseNumbers :+ InUseNumber(s.head, InUse = true, repeated = false))
//      }
//    }
//  }
//
//  @tailrec
//  def addTheRestOfTheNumbers(listInUseNumbers: List[InUseNumber], nSquare: Int = 9): List[InUseNumber] = {
//    if(nSquare <= 0) listInUseNumbers
//    else if(listInUseNumbers.map(_.number).contains(nSquare)) addTheRestOfTheNumbers(listInUseNumbers, nSquare-1)
//    else addTheRestOfTheNumbers(listInUseNumbers :+ InUseNumber(nSquare, InUse = false, repeated = false), nSquare-1)
//  }
//
//  // verify methods
//  def verifyRow(s: Array[Array[Int]], row: Int, objective: Int = 15): Boolean = {
//    s(row).sum == objective
//  }
//
//  def verifyColumn(s: Array[Array[Int]], column: Int, objective: Int = 15): Boolean = {
//    s.map(x => x(column)).sum == objective
//  }
//
//  def verifyDiagonal(s: Array[Array[Int]], diagonal: Diagonal, objective: Int = 15): Boolean = {
//    diagonal match {
//      case D1 => s(0)(0) + s(1)(1) + s(2)(2) == objective
//      case D2 => s(0)(2) + s(1)(1) + s(2)(0) == objective
//    }
//  }
//
//  def isMagicSquare(s: Array[Array[Int]]): Boolean = {
//    verifyRow(s,0) && verifyRow(s,1) && verifyRow(s,2) && verifyColumn(s, 0) &&
//      verifyColumn(s, 1) && verifyColumn(s, 2) && verifyDiagonal(s, diagonal = D1) &&
//      verifyDiagonal(s, diagonal = D2)
//  }
//
//  def verifyAllDifferent(s: Array[Int], setInUseNumbers: Set[Int] = Set()): Boolean = {
//    if(s.isEmpty) true
//    else if(setInUseNumbers.contains(s.head)) false
//    else verifyAllDifferent(s.tail, setInUseNumbers + s.head)
//  }
//
//
//
//}
//
//object FormingMagicSquareSolution {
//  def main(args: Array[String]) {
//    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
//
//    val s = Array.ofDim[Int](3, 3)
//
//    for (i <- 0 until 3) {
//      s(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
//    }
//
//    val result = FormingMagicSquareResult.formingMagicSquare(s)
//
//    println(result)
//
//    //printWriter.close()
//  }
//}
//
