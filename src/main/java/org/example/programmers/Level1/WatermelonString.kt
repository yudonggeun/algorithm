package org.example.programmers.Level1

//https://school.programmers.co.kr/learn/courses/30/lessons/12922
class WatermelonString {
    fun solution(n: Int): String = String(CharArray(n) { i -> if (i % 2 == 0) '수' else '박' })
}