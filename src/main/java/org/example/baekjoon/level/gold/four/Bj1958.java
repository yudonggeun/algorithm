package org.example.baekjoon.level.gold.four;

import java.io.*;
import java.util.Arrays;

public class Bj1958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine();
        B = br.readLine();
        C = br.readLine();

        dp = new int[A.length()][B.length()][C.length()];
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int result = lcs(0, 0, 0);
        System.out.println(result);
    }

    public static int[][][] dp;
    public static String A, B, C;

    public static int lcs(int a, int b, int c) {

        if (a == A.length() || b == B.length() || c == C.length()) {
            return 0;
        }
        if (dp[a][b][c] != -1) {
            return dp[a][b][c];
        }

        if (A.charAt(a) == B.charAt(b) && B.charAt(b) == C.charAt(c)) {
            return dp[a][b][c] = 1 + lcs(a + 1, b + 1, c + 1);
        } else {
            int case1 = lcs(a + 1, b, c);
            int case2 = lcs(a, b + 1, c);
            int case3 = lcs(a, b, c + 1);

            int max = Math.max(case1, Math.max(case2, case3));
            return dp[a][b][c] = max;
        }
    }
}
