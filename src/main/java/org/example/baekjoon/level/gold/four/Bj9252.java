package org.example.baekjoon.level.gold.four;

import java.io.*;

public class Bj9252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = br.readLine();
        String b = br.readLine();
        dp = new String[a.length() + 1][b.length() + 1];

        String result = lcs(a, b, 0, 0);
        System.out.println(result.length());
        System.out.println(result);
    }

    public static String[][] dp;

    public static String lcs(String a, String b, int ia, int ib) {

        if (dp[ia][ib] != null) {
            return dp[ia][ib];
        }

        if (a.length() == ia || b.length() == ib) {
            return "";
        }

        if (a.charAt(ia) == b.charAt(ib)) {
            return dp[ia][ib] = a.charAt(ia) + lcs(a, b, ia + 1, ib + 1);
        } else {
            String lcs1 = lcs(a, b, ia + 1, ib);
            String lcs2 = lcs(a, b, ia, ib + 1);
            return dp[ia][ib] = lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        }
    }
}
