package org.example.baekjoon.level.gold.four;

import java.io.*;

public class Bj4429 {

    static int[][] board;

    static int ALL = (1 << 9) - 1;

    static int[] rowSet = {ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL};
    static int[] colSet = {ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL};
    static int[] sectorSet = {ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL, ALL};

    static int fill(int set, int i) {
        return set & ~(1 << i - 1);
    }

    static int rollback(int set, int i) {
        return set + (1 << i - 1);
    }

    static void set(int row, int col, int value) {
        board[row][col] = value;
        rowSet[row] = fill(rowSet[row], value);
        colSet[col] = fill(colSet[col], value);
        int sectorId = sectorId(row, col);
        sectorSet[sectorId] = fill(sectorSet[sectorId], value);
    }

    static void rollback(int row, int col, int value) {
        board[row][col] = 0;
        rowSet[row] = rollback(rowSet[row], value);
        colSet[col] = rollback(colSet[col], value);
        int sectorId = sectorId(row, col);
        sectorSet[sectorId] = rollback(sectorSet[sectorId], value);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            char[] input = br.readLine().trim().toCharArray();

            for (int j = 0; j < 9; j++) {
                set(i, j, input[j] - '0');
            }
        }

        dfs(0);

        for (int[] row : board) {
            for (int t : row) {
                System.out.print(t);
            }
            System.out.println();
        }
    }

    static boolean dfs(int p) {
        if (p == 81) return true;

        int row = p / 9;
        int col = p % 9;

        if (board[row][col] != 0) {
            return dfs(p + 1);
        } else {
            int candidate = rowSet[row] & colSet[col] & sectorSet[sectorId(row, col)];
            for (int k = 0; k < 9; k++) {
                if ((candidate & (1 << k)) > 0) {
                    int target = k + 1;
                    set(row, col, target);
                    if (dfs(p + 1)) return true;
                    rollback(row, col, target);
                }
            }
        }
        return false;
    }

    static int sectorId(int row, int col) {
        return (row / 3) * 3 + col / 3;
    }
}
