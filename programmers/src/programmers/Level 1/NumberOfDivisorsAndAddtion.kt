//https://school.programmers.co.kr/learn/courses/30/lessons/77884
import kotlin.math.sqrt

class Solution {
    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0
        for (num in left..right)
            if (doesHaveOddNumberOfDivisor(num)) answer -= num else answer += num
        return answer
    }

    private fun doesHaveOddNumberOfDivisor(number: Int): Boolean 
    = sqrt(number.toDouble()).toInt().toDouble() == sqrt(number.toDouble()) 
}