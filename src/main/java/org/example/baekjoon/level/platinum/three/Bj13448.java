package org.example.baekjoon.level.platinum.three;

import java.io.*;
import java.util.*;

public class Bj13448 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[] M = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] P = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] R = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        N = input[0];
        T = input[1];

        dp = new long[51][100001];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        tests = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tests.add(new Test(M[i], P[i], R[i]));
        }
        tests.sort(Comparator.comparingDouble(a -> a.order));

        System.out.println(answer(0, 0));
    }

    static long[][] dp;
    static List<Test> tests;
    static int N;
    static int T;

    static long answer(int idx, long time) {

        if (idx == N) return 0;
        long ret = dp[idx][(int) time];

        if (ret != -1) return ret;

        ret = 0;
        if (idx + 1 <= N && time + tests.get(idx).r <= T) {
            Test test = tests.get(idx);
            ret = Math.max(ret, answer(idx + 1,  time + test.r) + test.m - (time + test.r) * test.p);
        }

        if (idx + 1 <= N) {
            ret = Math.max(ret, answer(idx + 1, time));
        }

        return dp[idx][(int) time] = ret;
    }

    static class Test {
        public final long m;
        public final long p;
        public final long r;
        public final double order;

        public Test(long m, long p, long r) {
            this.m = m;
            this.p = p;
            this.r = r;
            order = r / (double) p;
        }
    }
}
