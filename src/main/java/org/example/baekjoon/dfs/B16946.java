package org.example.baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B16946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n * m];
        size = new int[n * m];
        Arrays.fill(size, 1);
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            var input = br.readLine().toCharArray();
            for (int j = 0; j < input.length; j++) {
                board[i][j] = input[j] - '0';
            }
        }

        //combine set
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] != 0) continue;
                int index = index(r, c, m);
                for (var p : move) {
                    int nr = r + p[0];
                    int nc = c + p[1];
                    if (!isRange(nr, nc, board) || board[nr][nc] != 0) continue;
                    int nIndex = index(nr, nc, m);
                    union(index, nIndex);
                }
            }
        }

        var sb = new StringBuilder();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] == 0) sb.append("0");
                else sb.append(getCount(r, c, board) % 10);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int getCount(int r, int c, int[][] board) {
        var set = new HashSet<Integer>();
        var count = 1;
        for (var m : move) {
            int nr = r + m[0];
            int nc = c + m[1];

            if (!isRange(nr, nc, board) || board[nr][nc] == 1) continue;
            int index = find(index(nr, nc, board[0].length));
            if (set.contains(index)) continue;
            set.add(index);
            count += size[index];
        }
        return count;
    }

    static boolean isRange(int r, int c, int[][] board) {
        return 0 <= r && r < board.length && 0 <= c && c < board[0].length;
    }

    static int[][] move = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };

    static int[] parent;
    static int[] size;

    static int index(int row, int col, int colSize) {
        return row * colSize + col;
    }

    static int find(int e) {
        if (parent[e] == e) return e;
        else return parent[e] = find(parent[e]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
            size[pa] += size[pb];
        }
    }
}