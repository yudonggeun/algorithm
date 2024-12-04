package org.example.baekjoon.FloydWarshall;

import java.util.*;
import java.io.*;

public class Bj1956 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int nodeCount = input[0];
        int vertexCount = input[1];

        int[][] map = new int[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < vertexCount; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int from = input[0] - 1;
            int to = input[1] - 1;
            int distance = input[2];

            map[from][to] = distance;
        }

        for (int mid = 0; mid < nodeCount; mid++) {
            for (int from = 0; from < nodeCount; from++) {
                for (int to = 0; to < nodeCount; to++) {
                    if (map[from][mid] == Integer.MAX_VALUE || map[mid][to] == Integer.MAX_VALUE) continue;
                    map[from][to] = Math.min(map[from][to], map[from][mid] + map[mid][to]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int node = 0; node < nodeCount; node++) {
            answer = Math.min(answer, map[node][node]);
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
