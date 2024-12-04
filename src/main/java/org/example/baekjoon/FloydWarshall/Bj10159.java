package org.example.baekjoon.FloydWarshall;

import java.io.*;
import java.util.*;

public class Bj10159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        int[][] map = new int[nodeCount][nodeCount];
        int[][] reversedMap = new int[nodeCount][nodeCount];

        init(map);
        init(reversedMap);

        for (int i = 0; i < edgeCount; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int small = input[0] - 1;
            int large = input[1] - 1;

            map[small][large] = 1;
            reversedMap[large][small] = 1;
        }

        floydWarshall(map, nodeCount);
        floydWarshall(reversedMap, nodeCount);

        for (int node = 0; node < nodeCount; node++) {
            int count = 0;
            for (int to = 0; to < nodeCount; to++) {
                if (map[node][to] != Integer.MAX_VALUE || reversedMap[node][to] != Integer.MAX_VALUE) {
                    count++;
                }
            }

            System.out.println(nodeCount - count);
        }
    }

    static void floydWarshall(int[][] map, int nodeCount) {
        for (int mid = 0; mid < nodeCount; mid++) {
            for (int from = 0; from < nodeCount; from++) {
                for (int to = 0; to < nodeCount; to++) {
                    if (map[from][mid] == Integer.MAX_VALUE || map[mid][to] == Integer.MAX_VALUE)
                        continue;
                    map[from][to] = Math.min(map[from][mid] + map[mid][to], map[from][to]);
                }
            }
        }
    }

    static void init(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }
    }
}
