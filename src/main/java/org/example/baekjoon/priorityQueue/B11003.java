package org.example.baekjoon.priorityQueue;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B11003 {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken());
        int l = Integer.parseInt(input.nextToken());

        var st = new StringTokenizer(br.readLine());

        var deque = new LinkedList<Data>();

        for (int i = 0; i < n; i++) {
            int data = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.peekLast().value > data) {
                deque.pollLast();
            }

            deque.offer(new Data(i, data));

            if (!deque.isEmpty() && deque.peek().index < i - l + 1) {
                deque.poll();
            }
            int min = deque.peek().value;
            bw.write(min + " ");
        }
        bw.flush();
    }

    static class Data {
        int index;
        int value;

        public Data(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
