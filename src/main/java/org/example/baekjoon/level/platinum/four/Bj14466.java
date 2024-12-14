package org.example.baekjoon.level.platinum.four;

import java.io.*;
import java.util.*;

public class Bj14466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        boolean[][][][] map = new boolean[N][N][N][N];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;

            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            map[r1][c1][r2][c2] = true;
            map[r2][c2][r1][c1] = true;
        }

        init(N);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            int index = getIndex(r, c, N);
            size[index]++;
        }

        for (int sr = 0; sr < N; sr++) {
            for (int sc = 0; sc < N; sc++) {
                for (int[] m : move) {
                    int er = sr + m[0];
                    int ec = sc + m[1];

                    if (er == N || ec == N || map[sr][sc][er][ec]) {
                        continue;
                    }

                    int sIndex = getIndex(sr, sc, N);
                    int eIndex = getIndex(er, ec, N);
                    union(sIndex, eIndex);
                }
            }
        }

        Set<Integer> roots = new HashSet<>();

        for (int i = 0; i < parents.length; i++) {
            int ri = findRoot(i);
            if (ri == i) {
                roots.add(i);
            }
        }

        int answer = 0;

        for (Integer root : roots) {
            int count = size[root];
            K -= count;
            answer += (count * K);
        }

        System.out.println(answer);
    }

    static int[][] move = {{1, 0}, {0, 1}};

    static int[] parents, size;

    public static void union(int a, int b) {
        int ra = findRoot(a);
        int rb = findRoot(b);

        if (ra == rb) return;
        parents[ra] = rb;
        size[rb] += size[ra];
    }

    public static int findRoot(int i) {
        if (parents[i] != i) parents[i] = findRoot(parents[i]);
        return parents[i];
    }

    public static int getIndex(int r, int c, int n) {
        return r * n + c;
    }

    public static void init(int n) {
        n = n * n;
        parents = new int[n];
        Arrays.setAll(parents, i -> i);
        size = new int[n];
    }
}
