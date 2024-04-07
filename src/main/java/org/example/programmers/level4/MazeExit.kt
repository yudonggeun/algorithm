package org.example.programmers.level4

import java.util.*

class MazeExit {
    fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {

        val pq = PriorityQueue<Case>()
        val trapMap = HashMap<Int, Int>()
        for (index in 0..traps.lastIndex) {
            trapMap[traps[index]] = index
        }

        val firstMap = Array(n + 1) { IntArray(n + 1) { 0 } }
        for (road in roads) {
            val from = road[0]
            val to = road[1]
            val time = road[2]
            if (firstMap[from][to] == 0) {
                firstMap[from][to] = time
            } else {
                firstMap[from][to] = time.coerceAtMost(firstMap[from][to])
            }
        }

        val visitMap = HashMap<Int, HashMap<Int, Int>>()

        pq.add(Case(0, start, 0))
        while (pq.isNotEmpty()) {
            val case = pq.poll()

            if (case.node == end) {
                return case.time
            }

            for (nextNode in 1..n) {
                val time = time(case.node, nextNode, case.trapBit, firstMap, trapMap)
                if (time == 0) continue
                val nextTime = case.time + time
                visitMap.putIfAbsent(nextNode, HashMap())
                if (trapMap.containsKey(nextNode)) {
                    val nextBit = nextBit(case.trapBit, trapMap[nextNode]!!)
                    if (visitMap[nextNode]!!.getOrDefault(nextBit, Int.MAX_VALUE) > nextTime) {
                        pq.add(Case(nextTime, nextNode, nextBit))
                        visitMap[nextNode]!![nextBit] = nextTime
                    }
                } else {
                    if (visitMap[nextNode]!!.getOrDefault(case.trapBit, Int.MAX_VALUE) > nextTime) {
                        pq.add(Case(nextTime, nextNode, case.trapBit))
                        visitMap[nextNode]!![case.trapBit] = nextTime
                    }
                }
            }
        }

        return -1
    }

    fun time(from: Int, to: Int, bit: Int, map: Array<IntArray>, trapMap: Map<Int, Int>): Int {
        var count = 0
        if (trapMap.containsKey(from)) {
            val fromIndex = trapMap[from]!!
            if (bit and (1 shl fromIndex) > 0) count++
        }

        if (trapMap.containsKey(to)) {
            val toIndex = trapMap[to]!!
            if (bit and (1 shl toIndex) > 0) count++
        }
        return if (count == 1) {
            map[to][from]
        } else {
            map[from][to]
        }
    }
}

fun nextBit(bit: Int, trapIndex: Int): Int = bit xor (1 shl trapIndex)

data class Case(
    val time: Int,
    val node: Int,
    val trapBit: Int
) : Comparable<Case> {
    override fun compareTo(other: Case): Int = this.time - other.time
}
