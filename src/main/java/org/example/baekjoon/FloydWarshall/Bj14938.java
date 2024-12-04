package org.example.baekjoon.FloydWarshall;

import java.io.*;
import java.util.*;

public class Bj14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nodeCount = input[0];
        int maxDistance = input[1];
        int vertexCount = input[2];

        int[] items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            Arrays.fill(dp[i], 16);
            dp[i][i] = 0;
        }

        for (int i = 0; i < vertexCount; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = input[0] - 1;
            int to = input[1] - 1;
            int distance = input[2];
            dp[from][to] = distance;
            dp[to][from] = distance;
        }

        for (int mid = 0; mid < nodeCount; mid++) {
            for (int from = 0; from < nodeCount; from++) {
                for (int to = 0; to < nodeCount; to++) {
                    dp[from][to] = Math.min(dp[from][to], dp[from][mid] + dp[mid][to]);
                }
            }
        }

        int answer = 0;
        for (int point = 0; point < nodeCount; point++) {

            int itemCount = 0;
            for (int otherPoint = 0; otherPoint < nodeCount; otherPoint++) {
                if (dp[point][otherPoint] <= maxDistance) {
                    itemCount += items[otherPoint];
                }
            }
            answer = Math.max(answer, itemCount);
        }

        System.out.println(answer);
    }
}
