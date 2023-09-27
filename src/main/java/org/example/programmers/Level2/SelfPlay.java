package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/131130
import java.util.HashSet;
import java.util.Set;

class SelfPlay {
    public int solution(int[] cards) {
        int answer = 0;

        for (int a = 1; a <= cards.length; a++) {
            Set<Integer> openBox = new HashSet<>();
            int ac = getCount(a, openBox, cards);
            for (int b = 1; b <= cards.length; b++) {
                Set<Integer> initOpenBox = new HashSet<>(openBox);
                int bc = getCount(b, initOpenBox, cards);
                answer = Math.max(ac * bc, answer);
            }
        }
        return answer;
    }

    public int getCount(int index, Set<Integer> openBox, int[] cards) {
        int count = 0;
        while (!openBox.contains(index)){
            count++;
            openBox.add(index);
            index = cards[index-1];
        }
        return count;
    }
}