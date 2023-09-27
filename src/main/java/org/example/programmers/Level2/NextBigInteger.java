package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12911
class NextBigInteger  {
    public int solution(int n) {
        int count = Integer.bitCount(n);
        while(true){
            if(count == Integer.bitCount(++n))
                return n;
        }
    }
}