object Complementary {

  def findDivisors(n: Int): List[Int] = {
    def go(divisor: Int, divisors: List[Int] = List()): List[Int] = {
      if (divisor <= 0) divisors
      else if (n % divisor == 0) go(divisor - 1, divisors :+ divisor)
      else go(divisor - 1, divisors)
    }

    go(n)
  }

  def findGreatestCommonDivisor(divisorsA: List[Int], divisorsB: List[Int]): Int = {
    def go(divisorsA: List[Int]): Int = {
      if(divisorsA.isEmpty) -1
      else if(divisorsB.contains(divisorsA.head)) divisorsA.head
      else go(divisorsA.tail)
    }

    go(divisorsA)
  }

  /*
  * Prime Factorization Numbers References
  * https://ung.edu/learning-support/video-transcripts/prime-factorization-numbers.php#:~:text=Prime%20factorization%20is%20a%20process,5%20is%20a%20prime%20number.
  * https://openstax.org/books/prealgebra-2e/pages/2-5-prime-factorization-and-the-least-common-multiple
  * https://www.geeksforgeeks.org/print-all-prime-factors-of-a-given-number/
  */

  def primeFactors(n: Int, oddFactor: Int = 3,  primeFactorsMap: Map[Int, Int] = Map[Int, Int]().withDefaultValue(0)): Map[Int, Int] = {
    if(n <= 1) primeFactorsMap
    else if(n % 2 == 0) primeFactors(n/2, oddFactor,  primeFactorsMap + (2 -> (primeFactorsMap(2)+1)))
    else if(n % oddFactor == 0) primeFactors(n/oddFactor, oddFactor,  primeFactorsMap + (oddFactor -> (primeFactorsMap(oddFactor)+1)))
    else primeFactors(n, oddFactor+2,  primeFactorsMap)
  }

  def findLeastCommonMultiple(primeFactorsMapA: Map[Int, Int], primeFactorsMapB: Map[Int, Int]): Double = {
    val firstResult: Map[Int, Int] = combinePrimeFactorsMap(primeFactorsMapA, primeFactorsMapB)
    val secondResult: Map[Int, Int] = combinePrimeFactorsMap(primeFactorsMapB, primeFactorsMapA, firstResult)

    secondResult.map(map => Math.pow(map._1, map._2)).product
  }

  def combinePrimeFactorsMap(primeFactorsMapA: Map[Int, Int], primeFactorsMapB: Map[Int, Int],
    result: Map[Int, Int] = Map()): Map[Int, Int]  = {

    if(primeFactorsMapA.isEmpty) result
    else if(primeFactorsMapB.contains(primeFactorsMapA.head._1)) combinePrimeFactorsMap(primeFactorsMapA.tail,
      primeFactorsMapB,
      result + (primeFactorsMapA.head._1 -> Math.max(primeFactorsMapA.head._2, primeFactorsMapB(primeFactorsMapA.head._1))))
    else combinePrimeFactorsMap(primeFactorsMapA.tail, primeFactorsMapB,
      result + (primeFactorsMapA.head._1 -> primeFactorsMapA.head._2))
  }


}
