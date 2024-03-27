package org.example.programmers.level4

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class TreeTrinity {

    val map = HashMap<Int, HashSet<Int>>()
    fun solution(n: Int, edges: Array<IntArray>): Int {
        // init map
        for (edge in edges) {
            val r = edge[0]
            val c = edge[1]
            map.putIfAbsent(r, HashSet())
            map.putIfAbsent(c, HashSet())
            map[r]!!.add(c)
            map[c]!!.add(r)
        }

        val firstLeafInfo = getInfoList(1)[0].idx
        val firstInfoList = getInfoList(firstLeafInfo)
        if (firstInfoList[0].distance == firstInfoList[1].distance) {
            return firstInfoList[0].distance
        }
        val secondInfoList = getInfoList(firstInfoList[0].idx)
        return secondInfoList[0].distance.coerceAtMost(secondInfoList[1].distance)
    }

    private fun getInfoList(from: Int): List<Info> {
        val result = LinkedList<Info>()
        val queue = LinkedList<Info>()
        queue.add(Info(from, 0))

        val visit = HashSet<Int>();
        visit.add(from)

        while (queue.isNotEmpty()) {
            val node = queue.poll();
            result.add(node)

            for (nextIdx in map.getOrDefault(node.idx, HashSet())) {
                if (visit.contains(nextIdx)) {
                    continue;
                }
                queue.add(Info(nextIdx, node.distance + 1))
                visit.add(nextIdx)
            }
        }

        return result.sortedBy { -it.distance }
    }
}

data class Info(
    val idx: Int,
    val distance: Int,
)