package org.example.baekjoon.math

const val MAX = 1000_000_0000L
const val SIZE = 1_000_00
fun main() {
    initMobius()
    val k = readln().trim().toInt()
    var left: Long = 0
    var right: Long = MAX
    while (left <= right) {
        val mid = (left + right) / 2
        val compute = compute(mid)
        if (compute < k) {
            left = mid + 1;
        } else {
            right = mid - 1
        }
    }
    println(left)
}

lateinit var mobius: Array<Long>
fun initMobius() {
    mobius = Array(SIZE + 1) { 0 }
    mobius[1] = 1
    for (i in 1..SIZE) {
        var j = 2 * i
        while (j <= SIZE) {
            mobius[j] -= mobius[i]
            j += i
        }
    }
}

fun compute(n: Long): Long {
    var counts = 0L
    var i = 1L
    while (i * i <= n) {
        counts += mobius[i.toInt()] * (n / (i * i))
        i++
    }
    return counts
}
