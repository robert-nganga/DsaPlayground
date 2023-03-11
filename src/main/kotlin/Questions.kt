fun main(){
    val arr = intArrayOf(-1,-1,0,0,-1,-1)
    val map = hashMapOf<Int, String>(1 to "a", 2 to "b")
    println(isIsomorphic("not", "bet"))

    println(pivotIndex(arr))
}

/*
Question 4
Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
fun reverseList(head: ListNode?) {
    var current = head
    var first = head
    var second = first?.next

    while (second != null){}

}

/*
Question 3
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list.
The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.
 */
data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)
fun mergeTwoListsRecursively(list1: ListNode?, list2: ListNode?): ListNode?{
    if (list1 == null) return list2
    if (list2 == null) return list1

    return if (list1.`val` <= list2.`val`) {
        list1.next = mergeTwoLists(list1.next, list2)
        list1
    } else {
        list2.next = mergeTwoLists(list1, list2.next)
        list2
    }
}
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null) return list2
    if (list2 == null) return list1

    val head = ListNode(-1)
    var current = head
    var node1 = list1
    var node2 = list2

    while (node1 != null && node2 != null){
        if (node1.`val` < node2.`val`){
            current.next = node1
            node1 = node1.next
        }else{
            current.next = node2
            node2 = node2.next
        }
        current = current.next!!
    }

    current.next = node1 ?: node2

    return head.next

}

/*
Question 2
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.
 */
fun isIsomorphic(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val mappings = hashMapOf<Char, Char>()
    for (i in s.indices){
        if (mappings.contains(s[i])){
            if (mappings[s[i]] != t[i]) return false
        }else{
            if (mappings.containsValue(t[i])) return false
            mappings[s[i]] = t[i]
        }
    }

    return true
}

/*
Question 1
Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the
left of the index is equal to the sum of all the numbers strictly to the index's right.
If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.
 */
fun pivotIndex(nums: IntArray): Int {
    val leftSum = hashMapOf(0 to 0)
    var left = 0
    for (i in 1 until nums.size){
        left += nums[i-1]
        leftSum[i] = left
    }
    var rightSum = 0
    var answer = -1
    for (i in nums.size-1 downTo 0){
        rightSum += nums.getOrNull(i+1) ?: 0
        if (rightSum == leftSum[i]){
            answer = i
        }
    }

    return -1
}