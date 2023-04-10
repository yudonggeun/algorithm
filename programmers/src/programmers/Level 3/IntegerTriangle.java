//https://school.programmers.co.kr/learn/courses/30/lessons/43105
import java.util.Arrays;

class Solution {

    int[][] move = {{1, 1}, {1, 0}};

    public int solution(int[][] triangle) {

        int[][] dp = new int[triangle.length][];
        for (int i = 0; i < dp.length; i++) dp[i] = new int[triangle[i].length];
        dp[0][0] = triangle[0][0];

        for (int r = 0; r < triangle.length - 1; r++) {
            for (int c = 0; c < triangle[r].length; c++) {
                for (int[] m : move) {
                    int cr = r + m[0], cc = c + m[1];
                    dp[cr][cc] = Math.max(dp[cr][cc], dp[r][c] + triangle[cr][cc]);
                }
            }
        }

        return Arrays.stream(dp[dp.length-1]).max().getAsInt();
    }
}