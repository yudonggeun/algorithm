//https://school.programmers.co.kr/learn/courses/30/lessons/150369/solution_groups?language=kotlin
import java.util.*
import kotlin.math.max

class Solution {

    val trimZero = { stack: Stack<Int> -> while (!stack.isEmpty() && stack.peek() == 0) stack.pop() }

    val work = { stack: Stack<Int>, cap: Int ->
        var storage = cap
        while (!stack.isEmpty()) {
            if (storage == 0) break
            if (stack.peek() < storage) {
                storage -= stack.pop()
            } else if (stack.peek() > storage) {
                stack.add(stack.pop() - storage)
                storage = 0
            } else {
                stack.pop()
                storage = 0
            }
        }
    }

    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {

        val dStack = Stack<Int>()
        val pStack = Stack<Int>()

        for (delivery in deliveries) dStack.add(delivery)
        for (pickup in pickups) pStack.add(pickup)

        var answer: Long = 0

        while (!dStack.isEmpty() || !pStack.isEmpty()) {
            var dCap = cap
            var pCap = cap

            trimZero(dStack)
            trimZero(pStack)

            answer += max(dStack.size, pStack.size)

            work(dStack, dCap)
            work(pStack, pCap)
        }

        return answer * 2;
    }
}