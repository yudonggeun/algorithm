package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj1007 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            init();

            int n = Integer.parseInt(br.readLine());
            long[][] vectors = new long[n][];

            for (int j = 0; j < n; j++) {
                vectors[j] = Arrays.stream(br.readLine().split(" "))
                        .mapToLong(Long::parseLong)
                        .toArray();
            }

            pick(n, 0, vectors, new LinkedList<>());
            System.out.println(answer);
        }
    }

    public static void init() {
        answer = Double.MAX_VALUE;
    }

    public static double answer = Double.MAX_VALUE;

    public static void pick(int n, int start, long[][] candidate, Deque<Integer> picked) {

        if (picked.size() == n / 2) {
            long x = 0;
            long y = 0;
            for (long[] vector : candidate) {
                x += vector[0];
                y += vector[1];
            }
            for (int pickIndex : picked) {
                long[] vector = candidate[pickIndex];
                x -= 2 * vector[0];
                y -= 2 * vector[1];
            }

            answer = Math.min(answer, Math.sqrt(x * x + y * y));
            return;
        }

        for (int i = start; i < n; i++) {
            picked.push(i);
            pick(n, i + 1, candidate, picked);
            picked.pop();
        }
    }
}
