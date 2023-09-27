//https://school.programmers.co.kr/learn/courses/30/lessons/70128
class Solution {
    fun solution(a: IntArray, b: IntArray): Int {
        var answer = 0;
        a.forEachIndexed{ index, value ->
            answer += value * b[index]
        }
        return answer
    }
}