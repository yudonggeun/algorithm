package org.example.baekjoon.level.platinum.five;

import java.io.*;
import java.util.*;

public class Bj1981 {

    static int n;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n][];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int start = 0;
        int end = 200;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (isPossible(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    static boolean isPossible(int gap) {
        for (int from = 0; from <= 200 - gap; from++) {
            int to = from + gap;
            if (bfs(from, to)) return true;
        }
        return false;
    }

    static boolean bfs(int from, int to) {

        int firstTarget = board[0][0];
        if(firstTarget < from || firstTarget > to) return false;
        boolean[][] visited = new boolean[n][n];
        Queue<Node> queue = new LinkedList<>();
        Node firstNode = new Node();
        firstNode.r = 0;
        firstNode.c = 0;
        queue.add(firstNode);
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.r == n - 1 && node.c == n - 1) {
                return true;
            }

            for (int[] m : move) {
                int nr = m[0] + node.r;
                int nc = m[1] + node.c;
                if (!isRange(nr, nc) || visited[nr][nc]) continue;
                int target = board[nr][nc];
                if (target < from || target > to) continue;

                Node next = new Node();
                next.r = nr;
                next.c = nc;

                visited[nr][nc] = true;
                queue.add(next);
            }
        }
        return false;
    }

    static class Node {
        int r, c;
    }

    static boolean isRange(int r, int c) {
        return r < n && c < n && r >= 0 && c >= 0;
    }

    static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
