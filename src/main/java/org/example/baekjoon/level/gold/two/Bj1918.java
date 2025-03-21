package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj1918 {

    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] input = br.readLine().trim().toCharArray();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('(', 0);
        map.put(')', 0);

        Stack<Character> ops = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            if ('A' <= c && c <= 'Z') {
                sb.append(c);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    sb.append(ops.pop());
                }
                if (!ops.isEmpty()) ops.pop();
            } else {
                while (!ops.isEmpty() && map.get(ops.peek()) >= map.get(c)) {
                    Character op = ops.pop();
                    sb.append(op);
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            Character op = ops.pop();
            if (op != '(' && op != ')') {
                sb.append(op);
            }
        }

        System.out.println(sb);
    }
}
