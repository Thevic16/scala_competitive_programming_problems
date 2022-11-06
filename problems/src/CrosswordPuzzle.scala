import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

// https://www.hackerrank.com/challenges/crossword-puzzle/problem

/*
1 - Identificar todos los cupos disponibles horizontales y verticales por tamano y posicion en orden.
    por ejemplo.
    - Una lista de cupos horizontales con la coordenadas de fila y columna de inicio.
    - Una lista de cupos verticales con la coordenadas de fila y columna de inicio.
    - Podria ser la misma lista solo que con un identificador para saber si es horizontal o vertial.
      (Se podria hacer con una lista de algun case class). Y tambien que tenga informacion del indice de la letra
      comparte con el siguiente palabra (None si el la primera o ultima palabra).

2 - Crear otro clase class que agrupe la palabra con su posicion en la grafica.

3 - Hago un algoritmo recursivo que filter las palabras disponibles por el tamano en la posicion correspondiente, si hay
    mas de una opcion utilizo un mapa para llamar recursivamente la funcion probando cada una de las opciones posibles.
    Que la funcion recursiva sea un Unit si nos damos cuenta que estamos en el camino equivocado retornar (), si estamos
    en el correcto imprimir la solution. (Hay que hacer una funcion que permita imprimir el resultado creo que podria ser
    manejable por toda la informacion tenemos de los case clases pasados).
*/

object CrosswordPuzzleResult {
  abstract class Direction

  case object Vertical extends Direction

  case object Horizontal extends Direction

  case class Position(row: Int, column: Int)

  case class Space(startPosition: Position, endPosition: Position, length: Int, direction: Direction)

  case class MaxContinueLengthResult(maxLength: Int, startPosition: Int)

  case class WordSpaceMatch(word: String, space: Space)

  var arraySpace: Array[Space] = Array()
  var arrayWordSpaceMatch: Array[WordSpaceMatch] = Array()


  /*
   * Complete the 'crosswordPuzzle' function below.
   *
   * The function is expected to return a STRING_ARRAY.
   * The function accepts following parameters:
   *  1. STRING_ARRAY crossword
   *  2. STRING words
   */

  def crosswordPuzzle(crossword: Array[String], words: String): Array[String] = {
    // Write your code here
    val arrayWords: Array[String] = words.split(";")
    addSpacesHorizontal(crossword)
    addSpacesVertical(crossword)

    getListWordSpaceMatch(arraySpace, arrayWords)

    getSolutionCrossword(crossword, arrayWordSpaceMatch)
  }

  // addSpaces process ---------------------------------------------------------------------------------------
  def addSpacesHorizontal(crossword: Array[String]): Unit = {
    var rowPosition: Int = 0
    crossword.foreach { row =>
      val continueLengthResult: MaxContinueLengthResult = getMaxContinueLength('-', row)
      val spaceLength: Int = continueLengthResult.maxLength

      if (spaceLength > 1) {
        val columnPosition = continueLengthResult.startPosition
        arraySpace = arraySpace :+ Space(Position(rowPosition, columnPosition), Position(rowPosition,
          columnPosition + spaceLength - 1), spaceLength, Horizontal)
      }
      rowPosition += 1
    }
  }

  def addSpacesVertical(crossword: Array[String]): Unit = {
    val maxNumberOfColumn: Int = crossword(0).length

    def go(columnPosition: Int = 0): Unit = {
      if (columnPosition >= maxNumberOfColumn) ()
      else {
        val columnString: Array[String] = crossword.map(row => row(columnPosition).toString)
        val continueLengthResult: MaxContinueLengthResult =
          getMaxContinueLength('-', columnString.fold("")(_ + _))

        val spaceLength = continueLengthResult.maxLength

        if (spaceLength > 1) {
          val rowPosition = continueLengthResult.startPosition
          arraySpace = arraySpace :+ Space(Position(rowPosition, columnPosition),
            Position(rowPosition + spaceLength - 1, columnPosition), spaceLength, Vertical)
        }
        go(columnPosition + 1)
      }
    }

    go()
  }

  // Utilities methods for addSpaces process -----------------------------------------------------------------
  def getMaxContinueLength(character: Char, arrayOfCharacter: String): MaxContinueLengthResult = {

    def go(array: String, previewsCharacter: Char = ' ',
           currentLengthCount: Int = 0, positionCount: Int = 0,
           continueLengthResult: MaxContinueLengthResult = MaxContinueLengthResult(-1, -1)):
            MaxContinueLengthResult = {
      if (array.isEmpty) continueLengthResult

      else if (previewsCharacter == character && previewsCharacter == array.head) {
        val currentContinueLengthResult: MaxContinueLengthResult =
          if (continueLengthResult.maxLength > currentLengthCount + 1) continueLengthResult
          else MaxContinueLengthResult(currentLengthCount + 1, positionCount - currentLengthCount)

        go(array.tail, previewsCharacter, currentLengthCount + 1, positionCount + 1,
          currentContinueLengthResult)
      }

      else if (array.head == character) {
        val currentContinueLengthResult: MaxContinueLengthResult =
          if (continueLengthResult.maxLength > 1) continueLengthResult
          else MaxContinueLengthResult(1, positionCount)

        go(array.tail, array.head, 1, positionCount + 1, currentContinueLengthResult)
      }

      else go(array.tail, array.head, 0, positionCount + 1, continueLengthResult)
    }

    go(arrayOfCharacter)
  }

  // getListWordSpaceMatch process ---------------------------------------------------------------------------
  def getListWordSpaceMatch(arraySpace: Array[Space], arrayWords: Array[String],
                            listWordSpaceMatch: Array[WordSpaceMatch] = Array()): Unit = {
    if (arraySpace.isEmpty) {
      if (verifyArrayWordSpaceMatch(listWordSpaceMatch))
        arrayWordSpaceMatch = listWordSpaceMatch
      else ()
    } else {
      val space = arraySpace.head
      val filterArrayWord = arrayWords.filter(_.length == space.length)

      filterArrayWord.foreach {
        word =>
          getListWordSpaceMatch(arraySpace.tail, arrayWords.filter(_ != word),
            listWordSpaceMatch :+ WordSpaceMatch(word, space))
      }
    }
  }

  // Utilities methods for getListWordSpaceMatch process -----------------------------------------------------
  def verifyArrayWordSpaceMatch(arrayWordSpaceMatch: Array[WordSpaceMatch]): Boolean = {

    def go(currentArrayWordSpaceMatch: Array[WordSpaceMatch], acc: Boolean = true): Boolean = {
      if (currentArrayWordSpaceMatch.isEmpty) acc
      else {
        val newAcc: Boolean = acc && verifyWordSpaceMatch(currentArrayWordSpaceMatch.head,
          arrayWordSpaceMatch.filter(_ != currentArrayWordSpaceMatch.head))
        go(currentArrayWordSpaceMatch.tail, newAcc)
      }
    }

    go(arrayWordSpaceMatch)
  }

  def verifyWordSpaceMatch(wordSpaceMatch: WordSpaceMatch,
                           othersWordSpaceMatch: Array[WordSpaceMatch]): Boolean = {
    var result: Boolean = true

    wordSpaceMatch match {
      case horizontalWSM@WordSpaceMatch(_, Space(_, _, _, Horizontal)) =>
        val verticalOthersWordSpaceMatch = othersWordSpaceMatch.filter(_.space.direction == Vertical)

        verticalOthersWordSpaceMatch.foreach {
          verticalWordSpaceMatch =>
              result = result && verifyWordSpaceMatchHorizontalCross(horizontalWSM, verticalWordSpaceMatch)
        }

      case verticalWSM@WordSpaceMatch(_, Space(_, _, _, Vertical)) =>
        val horizontalOthersWordSpaceMatch = othersWordSpaceMatch.filter(_.space.direction == Horizontal)

        horizontalOthersWordSpaceMatch.foreach {
          horizontalWordSpaceMatch =>
              result = result && verifyWordSpaceMatchVerticalCross(verticalWSM, horizontalWordSpaceMatch)
        }
    }

    result
  }

  def verifyWordSpaceMatchHorizontalCross(horizontalWSM: WordSpaceMatch,
                                          posibleCrossVerticalWSM: WordSpaceMatch): Boolean = {
    val horizontalPermanentRow: Int = horizontalWSM.space.startPosition.row
    val verticalPermanentColumn: Int = posibleCrossVerticalWSM.space.startPosition.column

    val horizontalWord: String = horizontalWSM.word
    val verticalWord: String = posibleCrossVerticalWSM.word

    if (isRowCross(horizontalPermanentRow, posibleCrossVerticalWSM.space.startPosition.row,
      posibleCrossVerticalWSM.space.endPosition.row) && isColumnCross(verticalPermanentColumn,
      horizontalWSM.space.startPosition.column, horizontalWSM.space.endPosition.column)) {
      val horizontalWordIndex: Int =  verticalPermanentColumn - horizontalWSM.space.startPosition.column
      val verticalWordIndex: Int = horizontalPermanentRow - posibleCrossVerticalWSM.space.startPosition.row

      horizontalWord(horizontalWordIndex) == verticalWord(verticalWordIndex)
    }
    else true
  }

  def verifyWordSpaceMatchVerticalCross(verticalWSM: WordSpaceMatch,
                                          posibleCrossHorizontalWSM: WordSpaceMatch): Boolean = {
    val verticalPermanentColumn: Int = verticalWSM.space.startPosition.column
    val horizontalPermanentRow: Int = posibleCrossHorizontalWSM.space.startPosition.row

    val verticalWord: String = verticalWSM.word
    val horizontalWord: String = posibleCrossHorizontalWSM.word


    if (isColumnCross(verticalPermanentColumn, posibleCrossHorizontalWSM.space.startPosition.column,
      posibleCrossHorizontalWSM.space.endPosition.column) && isRowCross(horizontalPermanentRow,
      verticalWSM.space.startPosition.row, verticalWSM.space.endPosition.row)) {
      val verticalWordIndex: Int = horizontalPermanentRow - verticalWSM.space.startPosition.row
      val horizontalWordIndex: Int = verticalPermanentColumn - posibleCrossHorizontalWSM.space.startPosition.column

      horizontalWord(horizontalWordIndex) == verticalWord(verticalWordIndex)
    }
    else true
  }

  // Utilities methods for verifyWordSpaceMatch process -------------------------------------------------
  def isColumnCross(column: Int, verticalStarColumn: Int, verticalEndColumn: Int): Boolean = {
    if (column >= verticalStarColumn && column <= verticalEndColumn) true
    else false
  }

  def isRowCross(row: Int, horizontalStarRow: Int, horizontalEndRow: Int): Boolean = {
    if (row >= horizontalStarRow && row <= horizontalEndRow) true
    else false
  }


  // getSolutionCrossword process ----------------------------------------------------------------------------
  def getSolutionCrossword(crossword: Array[String],
                           arrayWordSpaceMatch: Array[WordSpaceMatch]): Array[String] = {
    def go(crossword: Array[String], arrayWordSpaceMatch: Array[WordSpaceMatch]): Array[String] = {
      if (arrayWordSpaceMatch.isEmpty) crossword
      else go(insertWordCrossword(crossword, arrayWordSpaceMatch.head), arrayWordSpaceMatch.tail)
    }
    go(crossword, arrayWordSpaceMatch)
  }

  // Utilities methods for getSolutionCrossword process ------------------------------------------------------
  def insertWordCrossword(crossword: Array[String], wordSpaceMatch: WordSpaceMatch): Array[String] = {
    val startPosition = wordSpaceMatch.space.startPosition
    val endPosition = wordSpaceMatch.space.endPosition
    val word = wordSpaceMatch.word

    def go(crossword: Array[Char], word: String, row: Int = 0, column: Int = 0,
           solutionCrossword: Array[Char] = Array()): Array[Char] = {
      if (crossword.isEmpty) solutionCrossword
      else if (row >= startPosition.row && row <= endPosition.row &&
        column >= startPosition.column && column <= endPosition.column && word.nonEmpty) {
        if (column == 9)
          go(crossword.tail, word.tail, row + 1, 0, solutionCrossword :+ word.head)
        else go(crossword.tail, word.tail, row, column + 1, solutionCrossword :+ word.head)
      } else {
        if (column == 9)
          go(crossword.tail, word, row + 1, 0, solutionCrossword :+ crossword.head)
        else go(crossword.tail, word, row, column + 1, solutionCrossword :+ crossword.head)
      }
    }

    val solutionCrosswordCharFormat: Array[Char] = go(crossword.flatMap(_.map(_.toChar)),
      word)
    val solutionCrosswordStringFormat: Array[String] =
      transformCrosswordArrayCharToArrayString(solutionCrosswordCharFormat)
    solutionCrosswordStringFormat
  }

  def transformCrosswordArrayCharToArrayString(crosswordCharFormat: Array[Char]): Array[String] = {
    def go(crosswordCharFormat: Array[Char],
           crosswordStringFormat: Array[String] = Array()): Array[String] = {
      if (crosswordCharFormat.isEmpty) crosswordStringFormat
      else go(crosswordCharFormat.drop(10), crosswordStringFormat :+
        crosswordCharFormat.take(10).mkString("", "", ""))
    }

    val result = go(crosswordCharFormat)
//    println(result.mkString("\n"))
//    println(" ")
    result
  }

}

object CrosswordPuzzleSolution {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val crossword = Array.ofDim[String](10)

    for (i <- 0 until 10) {
      val crosswordItem = StdIn.readLine
      crossword(i) = crosswordItem
    }

    val words = StdIn.readLine

    val result = CrosswordPuzzleResult.crosswordPuzzle(crossword, words)

    println(result.mkString("\n"))

    //printWriter.close()
  }
}

