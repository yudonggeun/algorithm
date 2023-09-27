package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/148652
class bitRow  {
    public long solution(int n, long l, long r) {

        long answer = 0;
        int maxN = n;
        while (l <= r) {
            double pow = Math.pow(4, maxN - n);
            while (l % 5 != 1 && l <= r) {
                answer += getValue(0, n, l) * pow;
                l++;
            }
            while (r % 5 != 0 && l <= r) {
                answer += getValue(0, n, r) * pow;
                r--;
            }
            l = l / 5 + 1;
            r = r / 5;
            n--;
        }
        return answer;
    }

    public int getValue(int depth, int level, long index) {
        if (index % 5 == 3) return 0;
        if (level == depth) return 1;
        long nextIndex = index / 5;
        if (index % 5 != 0) nextIndex++;
        return getValue(depth, level - 1, nextIndex);
    }
}