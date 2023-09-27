package org.example.programmers.Level1

//https://school.programmers.co.kr/learn/courses/30/lessons/12918?language=kotlin
class StringControl {
    fun solution(s: String): Boolean {
        return (s.length == 4 || s.length == 6) && !".*[^0-9].*".toRegex().matches(s)
    }
}