package org.example.programmers.Level1

//https://school.programmers.co.kr/learn/courses/30/lessons/12917
class ArrayStringsInDescendingOrder {
    fun solution(s: String): String {
        return StringBuilder().append(s.toCharArray().sortedArrayDescending()).toString()
    }
}