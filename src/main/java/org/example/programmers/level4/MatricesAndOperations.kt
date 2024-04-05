package org.example.programmers.level4

import java.util.*

fun main() {
    val result = Solution().solution(
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        ),
        arrayOf("Rotate", "ShiftRow")
    )

    for (ints in result) {
        println(ints.contentToString())
    }
}


class Solution {

    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {

        val firstColNumbers = LinkedList<Int>()
        val middleNumbersRow = LinkedList<LinkedList<Int>>()
        val lastColNumbers = LinkedList<Int>()

        for (row in rc) {
            // first init
            firstColNumbers.addLast(row.first())
            // middle init
            val deque = LinkedList<Int>()
            middleNumbersRow.addLast(deque)
            for (idx in 1..<row.lastIndex) {
                deque.add(row[idx])
            }
            // last init
            lastColNumbers.addLast(row.last())
        }
        for (operation in operations) {
            when (operation) {
                "Rotate" -> rotate(firstColNumbers, lastColNumbers, middleNumbersRow)
                "ShiftRow" -> shiftRow(firstColNumbers, lastColNumbers, middleNumbersRow)
            }
        }

        for (row in rc) {
            val mid = middleNumbersRow.pollFirst()
            for (idx in 0..row.lastIndex) {
                row[idx] = when (idx) {
                    0 -> firstColNumbers.pollFirst()
                    row.lastIndex -> lastColNumbers.pollFirst()
                    else -> mid.pollFirst()
                }
            }
        }

        return rc
    }

    private fun rotate(
        firstNumbers: LinkedList<Int>,
        lastNumbers: LinkedList<Int>,
        middleNumberRow: LinkedList<LinkedList<Int>>
    ) {
        val frontRow = middleNumberRow.first()
        val endRow = middleNumberRow.last()

        frontRow.addFirst(firstNumbers.pollFirst())
        lastNumbers.addFirst(frontRow.pollLast())
        endRow.addLast(lastNumbers.pollLast())
        firstNumbers.addLast(endRow.pollFirst())
    }

    private fun shiftRow(
        firstNumbers: LinkedList<Int>,
        lastNumbers: LinkedList<Int>,
        middleNumberRow: LinkedList<LinkedList<Int>>
    ) {
        firstNumbers.addFirst(firstNumbers.pollLast())
        middleNumberRow.addFirst(middleNumberRow.pollLast())
        lastNumbers.addFirst(lastNumbers.pollLast())
    }
}