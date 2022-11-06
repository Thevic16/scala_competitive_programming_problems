object TestThings extends App{
  val crossword: Array[Array[Char]] = Array(Array('+','+'), Array('-','-'),  Array('+','-'))

  println(crossword.tail.mkString("Array(", ", ", ")"))
  println(crossword.tail.tail.mkString("Array(", ", ", ")"))

  println(crossword.head.tail.mkString("Array(", ", ", ")"))

  val arrayChar: Array[Char] = Array('+','+')
  println(arrayChar.mkString("", "", ""))
}
