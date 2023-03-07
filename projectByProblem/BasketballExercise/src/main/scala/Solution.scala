import scala.collection.mutable
import scala.io.StdIn

// https://codeforces.com/problemset/problem/1195/C
object Solution extends App {

    var memo: mutable.Map[(Int, Int), Int] = mutable.Map()
    val n: Int = StdIn.readLine.trim.toInt
    val h1s: List[Int] = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt).toList
    val h2s: List[Int] = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt).toList

    abstract class Choose
    case object Both extends Choose
    case object Up extends Choose
    case object Down extends Choose

    def dp(h1s: List[Int], h2s: List[Int], i: Int = 0, totalHeight: Int = 0, choose: Choose = Both): Int = {
      if(h1s.isEmpty && h2s.isEmpty) {
        totalHeight
      }
      else if(memo.isDefinedAt((i, totalHeight))){
        //println("memoooo!!:" + (i, totalHeight))
        memo((i, totalHeight))
      }
      else {
        choose match {
          case Both =>
            val result: Int = math.max(math.max(dp(h1s.tail, h2s.tail, i+1, totalHeight, Both),
              dp(h1s.tail, h2s.tail, i+1, totalHeight + h1s.head, Up)),
              dp(h1s.tail, h2s.tail, i+1, totalHeight + h2s.head, Down))

            memo.update((i, totalHeight), result)
            memo((i, totalHeight))

          case Up =>
            val result: Int = math.max(dp(h1s.tail, h2s.tail, i+1, totalHeight, Up),
              dp(h1s.tail, h2s.tail, i+1, totalHeight + h2s.head, Down))

            memo.update((i, totalHeight), result)
            memo((i, totalHeight))

          case Down =>
            val result: Int = math.max(dp(h1s.tail, h2s.tail, i+1, totalHeight, Down),
              dp(h1s.tail, h2s.tail, i+1, totalHeight + h1s.head, Up))

            memo.update((i, totalHeight), result)
            memo((i, totalHeight))
        }
      }
    }

    println(dp(h1s, h2s))
}
