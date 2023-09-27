package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12924
class ExpressNumber {
    public int solution(int n) {
        int count = 1;
        for (int i = 2; n > i; i++) {
            n -= i - 1;
            if (n % i == 0) {
                count++;
            }

            return count;
        }
        return 0;
    }
}