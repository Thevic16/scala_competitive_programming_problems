// https://leetcode.com/problems/split-a-string-in-balanced-strings/

object SplitStringBalanced extends App {
  def balancedStringSplit(s: String): Int = {
    def go(s:String, contR:Int = 0, contL:Int = 0, countBalanc: Int = 0): Int ={
      if(s.isEmpty) countBalanc
      else {
        val newCountR = if(s.head == 'R') contR +1 else contR
        val newCountL = if(s.head == 'L') contL +1 else contL
        go(s.tail, newCountR, newCountL, if(newCountR == newCountL) countBalanc + 1 else countBalanc)
      }
    }

    go(s)
  }

  println(balancedStringSplit("RLRRLLRLRL"))
  println(balancedStringSplit("RLRRRLLRLL"))
  println(balancedStringSplit("LLLLRRRR"))
}
