package org.example.programmers.Level1

//https://school.programmers.co.kr/learn/courses/30/lessons/12950
class MetricAdd {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        return arr1.mapIndexed { row, it -> it.mapIndexed {col, it -> it + arr2[row][col]}.toIntArray()}.toTypedArray()
    }
}