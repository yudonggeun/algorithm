package org.example.baekjoon.level.platinum.three;

import java.io.*;
import java.util.*;

public class Bj12963 {

    static long answer = 0;
    static int n, m;

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
        parent[ri] = rj;
    }

    static class Path {
        int from, to;
        long size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Stack<Path> paths = new Stack<>();

        long size = 1;
        for (int num = 0; num < m; num++) {
            st = new StringTokenizer(br.readLine());
            Path path = new Path();
            path.from = Integer.parseInt(st.nextToken());
            path.to = Integer.parseInt(st.nextToken());
            path.size = size;
            size = size * 3 % 1_000_000_007;
            paths.push(path);
        }

        init(n);
        while (!paths.isEmpty()) {
            Path path = paths.pop();
            int from = find(path.from);
            int to = find(path.to);
            int start = find(0);
            int end = find(n - 1);

            if ((from == start && to == end) || (from == end && to == start)) {
                answer = (answer + path.size) % 1_000_000_007;
            } else {
                union(from, to);
            }
        }

        System.out.println(answer);
    }
}
