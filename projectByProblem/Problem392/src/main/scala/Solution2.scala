object Solution2 {
  def isSubsequence(s: String, t: String, i: Int = 0): Boolean = {
    if (s.isEmpty) {
      true
    }
    else if (t.length < s.length) {
      false
    }
    else if (s == t) {
      true
    }
    else if (i >= t.length) {
      false
    }
    else {
      isSubsequence(s, t, i + 1) || isSubsequence(s, removeByIndex(t, i), i)
    }
  }

  def removeByIndex(s: String, targetIndex: Int, index: Int = 0, acc: String = ""): String = {
    if (s.isEmpty) {
      acc
    }
    else if (targetIndex != index) {
      removeByIndex(s.tail, targetIndex, index + 1, acc :+ s.head)
    }
    else {
      removeByIndex(s.tail, targetIndex, index + 1, acc)
    }
  }
}
