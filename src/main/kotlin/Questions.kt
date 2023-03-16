import java.util.*

fun main(){
//    val arr = intArrayOf(-1,-1,0,0,-1,-1)
//    val map = hashMapOf<Int, String>(1 to "a", 2 to "b")
//    println(isIsomorphic("not", "bet"))
//    println(pivotIndex(arr))
//
//    val list = ListNode(`val` = 6, next = ListNode(`val` = 2, next = ListNode(`val` = 7, next = null)))
//    println(reverseList(list))

    println()
    val list = TreeNode(`val` = 4)
    val list1 = TreeNode(`val` = 5)
    list1.left = TreeNode(`val` = 1)
    list1.right = TreeNode(`val` = 0)
    list.left = list1
    list.right = TreeNode(`val` = 6)
    println(levelOrder(list))
    val arr = intArrayOf(2,6,5,7)
}

/*
Question 8
Given the root of a binary tree,
return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 */
class TreeNode(var `val`: Int) {
     var left: TreeNode? = null
     var right: TreeNode? = null
}
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    if(root == null) return result
    val queue : Queue<TreeNode> = LinkedList()
    queue.add(root)
    while (queue.isNotEmpty()){
        val level = queue.size
        val temp = mutableListOf<Int>()
        for (i in 0 until level){
            val current = queue.remove()
            temp.add(current.`val`)
            if (current.left != null){
                queue.add(current.left)
            }
            if (current.right != null){
                queue.add(current.right)
            }
        }
        result.add(temp)
    }
    return result
}

/*
Question 7
Given the root of an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples)
 */
class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}
fun preorderStack(root: Node?): List<Int>{
    val result = mutableListOf<Int>()
    if (root == null) return result
    val stack = Stack<Node>()
    stack.push(root)
    while (stack.isNotEmpty()){
        val current = stack.pop()
        result.add(current.`val`)
        if (current.children.isNotEmpty()){
            val size = current.children.size -1
            for (i in size downTo 0 ){
                stack.push(current.children[i])
            }
        }
    }
    return result
}
fun preorder(root: Node?): List<Int> {
    val result = mutableListOf<Int>()
    if (root == null) return result
    result.add(root.`val`)
    var head = root
    head.children.forEach { child->
        if (child!= null){
            result.add(child.`val`)
            preOrderRecurse(child.children, result)
        }
    }

    return result
}

fun preOrderRecurse(nodes:List<Node?>, list: MutableList<Int>):List<Int>{
    if (nodes.isEmpty()) return list
    nodes.forEach {child->
        if (child!= null){
            list.add(child.`val`)
            preOrderRecurse(child.children, list)
        }
    }
    return list
}

/*
Question6
Given a string s which consists of lowercase or uppercase letters,
return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 */
fun longestPalindrome(s: String): Int {
    if (s.length == 1) return 1
    val map = hashMapOf<Char, Int>()
    for (str in s){
        if (map.contains(str)){
            map[str] = map[str]!! + 1
        }else{
            map[str] = 1
        }
    }
    var oddTimes = 0
    var evenTimes = 0
    var length = 0
    for ((_, num) in map){
        val remainder = num % 2
        if (remainder == 1){
            length += num-1
            oddTimes++
        }
        if (remainder == 0){
            length += num
            evenTimes++
        }
    }
    return if (oddTimes >= 1) length+1 else length
}

/*
Question 5
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.
 */
fun middleNode(head: ListNode?): ListNode? {
    var map = arrayOf<ListNode?>()
    var current = head
    while (current != null){
        map = map.plus(current)
        current = current.next
    }
    val middle = map.size/2
    return map[middle]

}

/*
Question 4
Given the head of a singly linked list, reverse the list, and return the reversed list.
 */

fun reverseListRecursively(head: ListNode?): ListNode?{

    if (head?.next == null) {
        return head
    }
    val newHead = reverseList(head.next)
    head.next?.next = head
    head.next = null
    return newHead

}

fun reverseList(head: ListNode?): ListNode? {
    var current = head
    var first = head
    var second = first?.next

    while (second != null){
        val temp = second.next
        second.next = first
        first = second
        second = temp
    }
    current?.next = null
    current = first
    return current
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