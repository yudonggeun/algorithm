package org.example.baekjoon.graph;

import java.io.*;
import java.util.*;

public class B2573 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());

        var board = new int[rowSize][];
        for (int i = 0; i < rowSize; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int time = 0;
        while (!isEnd(board)) {
            if (isDivide(board)) {
                System.out.println(time);
                return;
            }
            melting(board);
            time++;
        }

        System.out.println(0);
    }

    private static void melting(int[][] board) {
        var buffer = new int[board.length][board[0].length];

        for (int row = 1; row < board.length - 1; row++) {
            for (int col = 1; col < board[row].length - 1; col++) {
                int howManyMelt = 0;
                for (int[] m : move) {
                    if (board[row + m[0]][col + m[1]] == 0) {
                        howManyMelt++;
                    }
                }
                buffer[row][col] = howManyMelt;
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] -= buffer[row][col];
                board[row][col] = Math.max(board[row][col], 0);
            }
        }
    }

    private static boolean isDivide(int[][] board) {
        var visit = new boolean[board.length][board[0].length];
        var queue = new LinkedList<int[]>();

        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    if (queue.isEmpty()) {
                        visit[row][col] = true;
                        queue.add(new int[]{row, col});
                    }
                    count++;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            count--;

            for (int[] m : move) {
                int nr = target[0] + m[0];
                int nc = target[1] + m[1];
                if (board[nr][nc] != 0 && !visit[nr][nc]) {
                    queue.add(new int[]{nr, nc});
                    visit[nr][nc] = true;
                }
            }
        }
        return count != 0;
    }

    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static boolean isEnd(int[][] board) {
        for (int[] row : board) {
            for (int e : row) if (e > 0) return false;
        }
        return true;
    }
}
