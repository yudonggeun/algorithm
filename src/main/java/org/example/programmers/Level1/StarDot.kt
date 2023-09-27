package org.example.programmers.Level1

//https://school.programmers.co.kr/learn/courses/30/lessons/12969?language=kotlin
fun main(args: Array<String>) {
    val (a, b) = readLine()!!.split(' ').map(String::toInt)

    for(i in 1..b){
        for(j in 1..a){
            print("*")
        }
        println()
    }
}