package org.example.baekjoon.FloydWarshall;

import java.io.*;
import java.util.*;

public class Bj11780 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        int[][] map = new int[nodeCount][nodeCount];
        int[][] reversePath = new int[nodeCount][nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            Arrays.fill(reversePath[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < edgeCount; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int from = input[0] - 1;
            int to = input[1] - 1;
            int cost = input[2];

            if (cost < map[from][to]) {
                map[from][to] = cost;
                reversePath[from][to] = from;
            }
        }

        for (int mid = 0; mid < nodeCount; mid++) {
            for (int from = 0; from < nodeCount; from++) {
                for (int to = 0; to < nodeCount; to++) {
                    if (map[from][mid] == Integer.MAX_VALUE || map[mid][to] == Integer.MAX_VALUE) {
                        continue;
                    }

                    int newCost = map[from][mid] + map[mid][to];
                    if (map[from][to] > newCost) {
                        map[from][to] = newCost;
                        reversePath[from][to] = reversePath[mid][to];
                    }
                }
            }
        }

        // print min cost
        for (int[] row : map) {
            for (int i : row) {
                if (i == Integer.MAX_VALUE) {
                    bw.write("0 ");
                } else {
                    bw.write(i + " ");
                }
            }
            bw.newLine();
        }

        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (map[i][j] == Integer.MAX_VALUE || i == j) {
                    bw.write("0");
                    bw.newLine();
                    continue;
                }

                int mid = j;
                Stack<Integer> stack = new Stack<>();
                while (mid != i) {
                    stack.push(mid + 1);
                    mid = reversePath[i][mid];
                }

                bw.write((stack.size() + 1) + " ");
                bw.write((i + 1) + " ");
                while (!stack.isEmpty()) {
                    bw.write(stack.pop() + " ");
                }

                bw.newLine();
            }
        }

        bw.flush();
    }
}
