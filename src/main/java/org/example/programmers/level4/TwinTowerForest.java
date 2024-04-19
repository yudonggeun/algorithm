package org.example.programmers.level4;

public class TwinTowerForest {
    long max = 1_000_000_007;
    public int solution(int n, int count) {
        long[][] dp = new long[n + 1][n + 1];
        dp[1][1] = 1;
        for(int i = 1; i < n; i++){
            for(int c = 1; c <= i; c++){
                dp[i+1][c] += 2*i*dp[i][c];
                dp[i+1][c + 1] += dp[i][c];
                dp[i+1][c] = dp[i+1][c] % max;
                dp[i+1][c + 1] = dp[i+1][c + 1] % max;
            }
        }
        return (int) dp[n][count];
    }
}
