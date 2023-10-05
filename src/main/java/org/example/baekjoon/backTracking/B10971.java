package org.example.baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B10971 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[][] board = new int[size][];

        for (int i = 0; i < size; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int node = 0; node < size; node++) {
            dfs(node, node, 0, new HashSet<>(), board);
        }

        System.out.println(min);

    }

    static int min = Integer.MAX_VALUE;

    public static void dfs(int start, int node, int sum, Set<Integer> visit, int[][] board) {
        if (visit.size() == board.length) {
            if (start == node) {
                min = Math.min(min, sum);
            }
            return;
        }

        for (int next = 0; next < board[node].length; next++) {
            int cost = board[node][next];

            if (cost != 0 && !visit.contains(next)) {
                visit.add(next);
                dfs(start, next, sum + cost, visit, board);
                visit.remove(next);
            }
        }
    }
}
