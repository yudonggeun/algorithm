package org.example.programmers.Level3;

public class Tile {
    public int solution(int n) {
        long MOD = 1000_000_007;
        int size = Math.max(7, n + 1);
        long[] dp = new long[size];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;

        for (int i = 6; i <= n; i++) {
            dp[i] = dp[i - 1] + (dp[i - 2] * 2) + (dp[i - 3] * 6) + dp[i - 4];
            if(dp[i] < dp[i-6]){
                dp[i] += MOD;
            }
            dp[i] -= dp[i - 6];
            dp[i] = dp[i] % MOD;
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        new Tile().solution(100_000);
    }
}