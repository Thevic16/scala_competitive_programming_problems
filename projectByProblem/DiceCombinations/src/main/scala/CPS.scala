import scala.annotation.tailrec

// https://stackoverflow.com/questions/37779102/continuation-passing-style-in-scala
// Continuation-passing style
object CPS extends App {
  type Cont[Hole,Result] = Hole => Result

  def f(x : Int) : Int = x + 1

  def fCps[Result](x: Int)(k: Cont[Int, Result]): Result = k(x + 1)

  //println(fCps(5)((x: Int) => x))

  def g(x: Int): Int = 2 * f(x)

  def gCps[Result](x: Int)(k: Cont[Int, Result]): Result = {
    fCps(x)(y => k(2 * y))
  }


  def sol(chars: List[Char], n: Int) = {
    def recSol(n: Int): List[List[Char]] = (chars, n) match {
      case (_, 0) => List(Nil)
      case (Nil, _) => Nil
      case (_, _) =>
        val tail = recSol(n - 1)
        chars.map(ch => tail.map(ch :: _ )).fold(Nil)(_ ::: _)
    }

    recSol(n).map(_.mkString).mkString(",")
  }

//  println(sol("op".toList, 1))
//  println(sol("op".toList, 2))
//  println(sol("op".toList, 3))

  def sol1(chars: List[Char], n: Int) = {
    @tailrec
    def recSol(n: Int)(cont: List[List[Char]] => List[List[Char]]): List[List[Char]] =
      {
        (chars, n) match {
          case (_, 0) => cont(List(Nil))
          case (Nil, _) => cont(Nil)
          case (_, _) =>
            recSol(n-1){tail => cont(chars.map(ch => tail.map(ch :: _)).fold(Nil)(_ ::: _))}
        }
      }
    recSol(n)(identity).map(_.mkString).mkString(",")
  }

//  println(sol1("op".toList, 1))
//  println(sol1("op".toList, 2))
//  println(sol1("op".toList, 3))

  def solCps[Result](chars: List[Char], n: Int)(k: Cont[String, Result]): Result = {
    @tailrec
    def recSol[Result](n: Int)(k: Cont[List[List[Char]], Result]): Result = {
      (chars, n) match {
        case (_, 0) => k(List(Nil))
        case (Nil, _) => k(Nil)
        case (_, _) => recSol(n-1)(tail => k(chars.map(ch => tail.map(ch :: _)).fold(Nil)(_ ::: _)))
      }
    }

    recSol(n)(result =>
      k(result.map(_.mkString).mkString(",")))
  }

  println(solCps("op".toList, 0)(identity))
    println(solCps("op".toList, 1)(identity))
    println(solCps("op".toList, 2)(identity))
    println(solCps("op".toList, 3)(identity))
}
