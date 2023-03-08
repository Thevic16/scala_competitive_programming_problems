import scala.collection.mutable
import scala.io.StdIn

// https://codeforces.com/problemset/problem/1791/E

object Solution extends App {

  var t: Int = StdIn.readLine.trim.toInt

//  sealed abstract class Sign
//  case object Minus extends Sign
//  case object Plus extends Sign

  while(t > 0){
    val memo: mutable.Map[(BigInt, BigInt), BigInt] = mutable.Map()
    val n: BigInt = StdIn.readLine.trim.toInt
    val as: List[BigInt] = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(x => BigInt(x.trim)).toList

    // Button-up approach
//    def dp(as: List[Int], i: Int = 0, suma: Int = 0): Int = {
//      if (as.length == 1) {
//        suma + as.head
//      }
//      else if (memo.isDefinedAt((i, as.head, suma))) {
//        //println("memooo!!!: " + (i, suma))
//        memo((i, as.head, suma))
//      }
//      else {
//        val result: Int = math.max(dp(as.tail, i + 1, suma + as.head),
//          dp(-as.tail.head +: as.tail.tail, i + 1, suma - as.head))
//
//        memo.update((i, as.head, suma), result)
//
//        memo((i, as.head, suma))
//      }
//    }

//    def getSign(number: Int): Sign = {
//      if (number < 0) {
//        Minus
//      }
//      else {
//        Plus
//      }
//    }

    // Up-button approach
    def dp(as: List[BigInt], i: BigInt): BigInt = {
      if (i == 0) {
        as.head
      }
      else if (memo.isDefinedAt(i, as.head)) {
        //println("memooo!!!: " + (i, suma))
        memo(i, as.head)
      }
      else {
        val result: BigInt =
          ( as.head + dp(as.tail, i - 1) ).max(
            ((- as.head) + dp(-as.tail.head +: as.tail.tail, i - 1)))

        memo.update((i, as.head), result)

        memo((i, as.head))
      }
    }

    println(dp(as, as.length - 1))
    t -= 1
  }
}
