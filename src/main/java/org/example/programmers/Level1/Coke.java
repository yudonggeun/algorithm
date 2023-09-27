package org.example.programmers.Level1;

//https://school.programmers.co.kr/learn/courses/30/lessons/132267
class Coke{
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while(n >= a){
            int left = n % a;
            int coke = n / a * b;
            n = coke + left;
            answer += coke;
        }
        return answer;
    }
}