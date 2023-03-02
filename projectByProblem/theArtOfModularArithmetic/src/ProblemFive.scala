
/*
*
* Problem 5: Prove that, for all primes p, the smallest positive integer whose factorial is divisible by p
*  is p itself.
*
* So, from the table it is clear that 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
*  71, 73, 79, 83, 89, 97 are the prime numbers.
*
* */
object ProblemFive extends App {
  val p: Int = 97

  def prove(p: Int, i: Int = 2): Int = {
    if(p % i == 0) i
    else prove(p, i + 1)
  }

  println(prove(p))
}
