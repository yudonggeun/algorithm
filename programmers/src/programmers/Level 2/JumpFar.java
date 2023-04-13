//https://school.programmers.co.kr/learn/courses/30/lessons/12914#
class Solution {
    public long solution(int n) {
        long answer = 0;
        long[][] dp = new long[n+1][n+1];

        for(int i = 0; i <= n; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        
        for(int m = 2; m <= n; m++){
            for(int r = 1; r < m; r++){
                dp[m][r] = (dp[m-1][r] + dp[m-1][r-1]) % 1234567l;
            }
        }
        
        for(int twoCount = 0; twoCount <= n/2; twoCount++){
            int m = n - twoCount;
            int r = twoCount;
            answer += dp[m][r];
            answer %= 1234567l;
        }
        return answer;
    }
}