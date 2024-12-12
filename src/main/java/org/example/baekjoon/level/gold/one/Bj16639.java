package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.Arrays;

public class Bj16639 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        expression = br.readLine().toCharArray();

        // init
        maxDp = new int[n][n];
        minDp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
            Arrays.fill(maxDp[i], Integer.MIN_VALUE);
        }
        int max = solution(0, n - 1)[0];
        System.out.println(max);
    }

    static char[] expression;
    static int[][] maxDp, minDp;

    static int[] solution(int start, int end) {

        if (maxDp[start][end] != Integer.MIN_VALUE && minDp[start][end] != Integer.MAX_VALUE)
            return new int[]{maxDp[start][end], minDp[start][end]};

        boolean isNumber = true;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int mid = start; mid <= end; mid++) {
            char c = expression[mid];

            if (c == '-') {
                isNumber = false;
                int[] right = solution(mid + 1, end);
                int[] left = solution(start, mid - 1);
                max = Math.max(max, left[0] - right[1]);
                min = Math.min(min, left[1] - right[0]);
            } else if (c == '+') {
                isNumber = false;
                int[] right = solution(mid + 1, end);
                int[] left = solution(start, mid - 1);
                max = Math.max(max, left[0] + right[0]);
                min = Math.min(min, left[1] + right[1]);
            } else if (c == '*') {
                isNumber = false;
                int[] right = solution(mid + 1, end);
                int[] left = solution(start, mid - 1);

                for (int l : left) {
                    for (int r : right) {
                        int tem = l * r;
                        max = Math.max(max, tem);
                        min = Math.min(min, tem);
                    }
                }
            }
        }

        if (isNumber) {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i <= end; i++) {
                sb.append(expression[i]);
            }
            max = Integer.parseInt(sb.toString());
            min = max;
        }
        maxDp[start][end] = max;
        minDp[start][end] = min;

        return new int[]{max, min};
    }
}
