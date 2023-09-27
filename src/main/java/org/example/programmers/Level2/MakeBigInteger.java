package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/42883#
import java.util.Deque;
import java.util.LinkedList;

class MakeBigInteger  {
    public String solution(String number, int k) {
        Deque<Character> stack = new LinkedList<>();
        int size = number.length() - k;

        for (int i = 0; i < number.length(); i++) {
            while (!stack.isEmpty() && number.charAt(i) > stack.peekLast() && k-- > 0) {
                stack.pollLast();
            }
            stack.add(number.charAt(i));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++)
            sb.append(stack.pollFirst());

        return sb.toString();
    }
}