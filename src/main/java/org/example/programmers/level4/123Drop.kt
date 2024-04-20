package org.example.programmers.level4
// https://school.programmers.co.kr/learn/courses/30/lessons/150364?language=kotlin

import kotlin.collections.*

class Drop123{
    fun solution(edges: Array<IntArray>, target: IntArray): IntArray {

        val nodeMap = HashMap<Int, Node>()
        nodeMap[0] = Node(0, target[0])

        for (edge in edges) {
            val parentNumber = edge[0] - 1
            val childNumber = edge[1] - 1

            nodeMap.putIfAbsent(parentNumber, Node(parentNumber, target[parentNumber]))
            nodeMap.putIfAbsent(childNumber, Node(childNumber, target[childNumber]))

            nodeMap[parentNumber]!!.addNode(nodeMap[childNumber]!!)
        }
        for (node in nodeMap.values) {
            node.leaves.sortWith { o1, o2 -> o1.number - o2.number }
        }

        // calculate leaf count
        val leavesCount = target.count { it > 0 }

        val numbers = mutableListOf<Int>()
        val finishedSet = mutableSetOf<Int>()

        val root = nodeMap[0]!!
        while (finishedSet.size < leavesCount) {
            val nextNode = root.getNextNode()
            nextNode.count++
            numbers.add(nextNode.number)
            if (nextNode.isFinished()) {
                finishedSet.add(nextNode.number)
            }
            if (nextNode.isImpossible()) {
                return intArrayOf(-1)
            }
        }

        val answer = mutableListOf<Int>()
        for (number in numbers.reversed()) {
            val margin = target[number] - nodeMap[number]!!.count
            nodeMap[number]!!.count--

            when {
                margin >= 2 -> {
                    target[number] -= 3
                    answer.add(3)
                }
                margin >= 1 -> {
                    target[number] -= 2
                    answer.add(2)
                }
                else -> {
                    target[number] -= 1
                    answer.add(1)
                }
            }
        }

        return answer.reversed().toIntArray()
    }

    class Node(val number: Int, private val maxCount: Int) {
        private var idx: Int = 0
        var count: Int = 0
        val leaves = ArrayList<Node>()

        fun addNode(node: Node) {
            leaves.add(node)
        }

        fun getNextNode(): Node {
            if (leaves.isEmpty()) return this
            val result = leaves[idx]
            idx = (idx + 1) % leaves.size
            return result.getNextNode()
        }

        fun isFinished(): Boolean = count * 3 >= maxCount
        fun isImpossible(): Boolean = maxCount < count
    }
}