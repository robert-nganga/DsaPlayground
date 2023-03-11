fun main() {
    val num = 99999
    //println(minMaxDifference(num))

    println("Answer ${findTheArrayConcVal(intArrayOf(2,4,3,6,4))}")
}

fun findTheArrayConcVal(nums: IntArray): Long {
    if (nums.size == 1) return nums[0].toLong()
    val arr = nums.toMutableList()
    var concat = 0
    while(arr.isNotEmpty()){
        if (arr.size == 1){
            concat += arr.removeFirst()
        }else{
            val concatValue = "${arr.removeFirst()}${arr.removeLast()}".toInt()
            concat += concatValue
        }
    }
    return concat.toLong()
}

/*
Question 1
You are given an integer num.
You know that Danny Mitral will sneakily remap one of the 10 possible digits (0 to 9) to another digit.
Return the difference between the maximum and minimum values Danny can make by remapping exactly one digit in num.

Notes:
When Danny remaps a digit d1 to another digit d2, Danny replaces all occurrences of d1 in num with d2.
Danny can remap a digit to itself, in which case num does not change.
Danny can remap different digits for obtaining minimum and maximum values respectively.
The resulting number after remapping can contain leading zeroes.
We mentioned "Danny Mitral" to congratulate him on being in the top 10 in Weekly Contest 326.
 */

fun minMaxDifference(num: Int): Int {
    val str = num.toString()
    val nine = '9'
    val zero = '0'
    var max = ""
    for (i in str.indices){
        if (str[i] != nine){
           max = str.replace(str[i], nine)
           break
        }
        max = str
    }
    println("Max:: $max")
    var min = ""
    for (i in str.indices){
        if (str[i] != zero){
            min = str.replace(str[i], zero)
            break
        }
        min = str
    }
    println("Min:: $min")
    return max.toInt() - min.toInt()
}