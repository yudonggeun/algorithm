package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj1781 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] input = new int[n][];

        for (int i = 0; i < n; i++) {
            // deadLine size
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(input, Comparator.comparing(o -> -o[1]));

        long sum = 0;
        init(n + 1);

        for (int[] row : input) {
            int deadLine = row[0];
            int size = row[1];

            int index = find(deadLine);
            while (index != deadLine) {
                deadLine = index;
                index = find(deadLine);
            }
            if (index != 0) {
                sum += size;
                union(deadLine, index - 1);
            }
        }

        System.out.println(sum);
    }

    static int[] parent;

    static void init(int size) {
        parent = new int[size];
        Arrays.setAll(parent, i -> i);
    }

    static int find(int i) {
        if (parent[i] == i) return i;
        else return parent[i] = find(parent[i]);
    }

    static void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);

        if (i < j) {
            parent[rj] = ri;
        } else {
            parent[ri] = rj;
        }
    }
}
