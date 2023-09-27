//https://school.programmers.co.kr/learn/courses/30/lessons/17687
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    private List<Character> cash = new ArrayList<>();
    private int number = 0;

    public String solution(int n, int t, int m, int p) {
        String answer = "";

        int turn = 0;
        while (turn < t) {
            char character = getChar(n, m, turn, p - 1);
            answer += character;
            turn++;
        }
        return answer.toUpperCase();
    }

    public char getChar(int n, int peopleSize, int turn, int myOrder) {
        int index = peopleSize * turn + myOrder;

        while (cash.size() <= index) {
            char[] plus = Integer.toString(number, n).toCharArray();
            System.out.println(Arrays.toString(plus));
            number++;
            for (char c : plus) {
                cash.add(c);
            }
        }
        return cash.get(index);
    }
}