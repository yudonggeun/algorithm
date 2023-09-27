package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/142085
import java.util.Comparator;
import java.util.PriorityQueue;

class DotPrint  {
    public int solution(int n, int k, int[] enemy) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -o));

        int index = 0;

        for (; index < enemy.length; index++) {
            int target = enemy[index];
            queue.add(target);
            n -= target;

            if(n < 0) {
                if(k == 0) break;
                k--;
                n += queue.poll();
            }
        }

        return index;
    }
}