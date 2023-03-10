import scala.collection.mutable

object Solution {

  def isSubsequence(s: String, t: String): Boolean = {
    var memo: mutable.Map[(Int, String), Boolean] = mutable.Map()

    def dp(s: String, t: String, i: Int = 0): Boolean = {
      if(s.isEmpty){
        true
      }
      else if(memo.isDefinedAt((i, t))){
        memo((i,t))
      }
      else if (t.length < s.length) {
        false
      }
      else if (s == t) {
        true
      }
      else if(i >= t.length){
        false
      }
      else {
        val result: Boolean = dp(s, t, i + 1) || dp(s, removeByIndex(t, i), i)
        memo.update((i, t), result)
        memo((i,t))
      }
    }

    dp(s, t)
  }

  def removeByIndex(s: String, targetIndex: Int, index: Int = 0, acc: String = ""): String = {
    if(s.isEmpty){
      acc
    }
    else if(targetIndex != index) {
      removeByIndex(s.tail, targetIndex, index + 1, acc :+ s.head)
    }
    else {
      removeByIndex(s.tail, targetIndex, index + 1, acc)
    }
  }

}

object Test extends App {
  val solution = Solution
  println(solution.isSubsequence("b", "abc"))
}
