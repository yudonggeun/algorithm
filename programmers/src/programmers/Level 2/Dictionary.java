//https://school.programmers.co.kr/learn/courses/30/lessons/84512
import java.util.*;

class Solution {

    public int solution(String word) {

        Deque<Character> deque = new LinkedList<>();
        int turn = 0;

        while (true) {
            next(deque); turn++;
            String result = toString(deque);
            System.out.println(result);
            if (result.equals(word)) return turn;
        }
    }

    private final char A = 'A', E = 'E', I = 'I', O = 'O', U = 'U';

    public void next(Deque<Character> deque) {
        if (deque.size() < 5) {
            deque.add(A);
            return;
        }
        while (true) {
            Character last = deque.pollLast();
            if (last == A) deque.add(E);
            else if (last == E) deque.add(I);
            else if (last == I) deque.add(O);
            else if (last == O) deque.add(U);
            if (last != U) break;
        }
    }

    public String toString(Deque<Character> deque) {
        StringBuilder sb = new StringBuilder();
        for (Character character : deque) sb.append(character);
        return sb.toString();
    }
}