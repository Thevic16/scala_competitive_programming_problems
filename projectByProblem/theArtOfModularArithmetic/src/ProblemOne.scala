import Complementary._

/*
  Problem 1: What is the largest positive integer that divides 40 and 78? What about the smallest
  positive integer divisible by 40 and 78?

  The greatest common divisor (gcd), of two or more non-zero integers, is the largest positive integer
   that divides the numbers without a remainder.

   LCM stands for least common multiple. The least common multiple of two numbers is the smallest number
    that is a multiple of both of them.
*/
object ProblemOne extends App {

  val a: Int = 40
  val b: Int = 78

  val divisorsA: List[Int] = findDivisors(a)
  val divisorsB: List[Int] = findDivisors(b)

  println(findGreatestCommonDivisor(divisorsA, divisorsB))

  println(primeFactors(a))
  println(primeFactors(b))

  println(findLeastCommonMultiple(primeFactors(a), primeFactors(b)))
}
