// https://leetcode.com/problems/two-sum/

object TwoSum extends App {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {

    def go(nums: Array[Int], map: Map[Int, Int], i: Int): Array[Int]= {
      val pair = target - nums.head
      if (map.contains(pair)) Array(map(pair), i)
      else go(nums.tail, map + (nums.head -> i), i +1)
    }

    go(nums, Map[Int,Int](), 0)
  }

  println(twoSum(Array(2, 7, 11, 15), 9).mkString("Array(", ", ", ")"))
  println(twoSum(Array(3,2,4), 6).mkString("Array(", ", ", ")"))
  println(twoSum(Array(3,3), 6).mkString("Array(", ", ", ")"))
}
