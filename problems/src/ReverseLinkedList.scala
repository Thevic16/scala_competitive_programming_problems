// https://leetcode.com/problems/reverse-linked-list/

// https://leetcode.com/problems/palindrome-linked-list/

object ReverseLinkedList extends App {


  // Definition for singly-linked list.
    class ListNode(_x: Int = 0, _next: ListNode = null) {
      var next: ListNode = _next
      var x: Int = _x
   }


  def reverseList(head: ListNode): ListNode = {
    def go(currentHead: ListNode, acc: ListNode): ListNode ={
      if(currentHead == null) acc
      else if(currentHead.next == null) new ListNode(currentHead.x, acc)
      else go(currentHead.next, new ListNode(currentHead.x, acc))
    }

    if (head != null)
      go(head.next, new ListNode(head.x))
    else
      null
  }

  def equal(head1: ListNode, head2: ListNode): Boolean = {
    if(head1 == null && head2 == null) true
    else if(head1.x != head2.x) false
    else equal(head1.next, head2.next)
  }

  def isPalindrome(head: ListNode): Boolean = {
    val reverseHead = reverseList(head)
    equal(head, reverseHead)
  }

 //val myListNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))))
 //val myListNode = new ListNode(1, null)
 val myListNode = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, null))))
 val myRevertListNode = reverseList(myListNode)

 println(isPalindrome(myRevertListNode))
}
