//https://school.programmers.co.kr/learn/courses/30/lessons/82612?language=kotlin
import kotlin.math.max

class Solution {
    fun solution(price: Int, money: Int, count: Int): Long {
        val need = price * (count * (count + 1)).toLong() /2 - money;
        return max(0, need)
    }
}