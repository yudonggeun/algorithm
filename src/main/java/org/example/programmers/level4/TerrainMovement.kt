package org.example.programmers.level4

import kotlin.math.abs

class TerrainMovement {
    fun solution(land: Array<IntArray>, height: Int): Int {

        init(land.size * land.size)
        // step1 : distinguish area
        for (r in 0..land.lastIndex) {
            for (c in 0..land[r].lastIndex) {
                val e = land[r][c]
                val idx = idx(r, c, land.size)
                for (m in move) {
                    val nr = r + m[0]
                    val nc = c + m[1]

                    if (isRange(nr, nc, land)) {
                        val ne = land[nr][nc]
                        if (abs(ne - e) <= height) {
                            val nIdx = idx(nr, nc, land.size)
                            union(idx, nIdx)
                        }
                    }
                }
            }
        }
        // step2 : found minimum cost cases
        val area = HashSet<Int>()
        for (idx in 0..parents.lastIndex) {
            if (idx == parents[idx]) {
                area.add(idx)
            }
        }
        val costMap = mutableMapOf<Int, MutableMap<Int, Int>>()

        for (r in 0..land.lastIndex) {
            for (c in 0..land[r].lastIndex) {
                val e = land[r][c]
                val idx = idx(r, c, land.size)
                val rIdx = find(idx)
                for (m in move) {
                    val nr = r + m[0]
                    val nc = c + m[1]

                    if (isRange(nr, nc, land)) {
                        val ne = land[nr][nc]
                        val nIdx = idx(nr, nc, land.size)
                        val nrIdx = find(nIdx)

                        if (rIdx != nrIdx) {
                            costMap.putIfAbsent(rIdx, HashMap())
                            costMap.putIfAbsent(nrIdx, HashMap())
                            val previousCost: Int = costMap[rIdx]!!.getOrDefault(nrIdx, 10001)
                            val cost = abs(ne - e)
                            costMap[rIdx]!![nrIdx] = previousCost.coerceAtMost(cost)
                            costMap[nrIdx]!![rIdx] = previousCost.coerceAtMost(cost)
                        }
                        if (abs(ne - e) <= height) {
                            union(idx, nIdx)
                        }
                    }
                }
            }
        }
        // step3 : calculate total minimum cost
        val costResult = mutableListOf<Info>()
        for (from in costMap.keys) {
            for (to in costMap[from]!!.keys) {
                val cost = costMap[from]!![to]!!
                costResult.add(Info(cost, from, to))
            }
        }

        costResult.sortBy { it.cost }

        var answer = 0
        for (info in costResult) {
            if (find(info.from) == find(info.to)) {
                continue
            }
            answer += info.cost
            union(info.from, info.to)
        }

        return answer
    }

    data class Info(
        val cost: Int,
        val from: Int,
        val to: Int,
    )

    fun init(len: Int) {
        parents = IntArray(len) { it }
        size = IntArray(len) { 1 }
    }

    fun isRange(r: Int, c: Int, array: Array<IntArray>): Boolean {
        val len = array.size;
        return 0 <= r && 0 <= c && r < len && c < len
    }

    val move = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, 1),
    )

    lateinit var parents: IntArray
    lateinit var size: IntArray

    fun idx(r: Int, c: Int, len: Int): Int = r * len + c

    fun union(a: Int, b: Int) {
        val ra = find(a)
        val rb = find(b)

        if (ra == rb) return
        if (size[ra] >= size[rb]) {
            parents[ra] = rb
            size[rb] += size[ra]
        } else {
            parents[rb] = ra
            size[ra] += size[rb]
        }
    }

    fun find(e: Int): Int {
        val root = parents[e];
        if (root == e) return root;
        parents[e] = find(root);
        return parents[e];
    }
}