package org.example.baekjoon.combination

import java.math.BigInteger
import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val nCr = Array(n + 1) { Array<BigInteger>(m + 1) { BigInteger.ZERO } }
    println(nCr(n, m, nCr))
}

fun nCr(n: Int, r: Int, dp: Array<Array<BigInteger>>): BigInteger {
    return when {
        dp[n][r] != BigInteger.ZERO -> dp[n][r]
        (n == r) or (r == 0) or (n == 1) -> BigInteger.ONE
        else -> {
            dp[n][r] = nCr(n - 1, r, dp) + nCr(n - 1, r - 1, dp)
            dp[n][r]
        }
    }
}