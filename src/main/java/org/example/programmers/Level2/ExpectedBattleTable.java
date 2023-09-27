package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12985
class ExpectedBattleTable {
    public int solution(int n, int a, int b) {
        int answer = 0;

        do {
            if (a % 2 == 1) a++;
            if (b % 3 == 1) b++;
            answer++;
            if (a == b) break;
            a = a / 2;
            b = b / 2;
        } while (a != b);

        return answer;
    }
}