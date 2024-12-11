package org.example.baekjoon.level.platinum.four;

import java.io.*;
import java.util.*;

public class Bj9376 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            char[][] board = new char[row + 2][col + 2];

            for (int r = 1; r <= row; r++) {
                char[] input = br.readLine().toCharArray();
                for (int c = 1; c <= col; c++) {
                    board[r][c] = input[c - 1];
                }
            }

            int answer = solution(board.length, board[0].length, board);
            bw.write(answer + "\n");
        }
        bw.flush();
    }

    public static char WALL = '*';
    public static char DOOR = '#';
    public static char PRISONER = '$';

    public static int[][] exitDp, prisoner1Dp, prisoner2Dp;

    public static int[][] initDp(int row, int col) {
        int[][] result = new int[row][col];
        for (int[] r : result) {
            Arrays.fill(r, INF);
        }
        return result;
    }

    public static int solution(int row, int col, char[][] board) {

        exitDp = initDp(row, col);
        prisoner1Dp = initDp(row, col);
        prisoner2Dp = initDp(row, col);

        List<int[]> prisoner = findPrisoner(board);

        bfs(0, 0, board, exitDp);
        bfs(prisoner.get(0)[0], prisoner.get(0)[1], board, prisoner1Dp);
        bfs(prisoner.get(1)[0], prisoner.get(1)[1], board, prisoner2Dp);

        // case 2 : 수감자가 각자 최소 경로로 나가는 경우
        int answer = prisoner1Dp[0][0] + prisoner2Dp[0][0];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // case 1 : 수감자가 만나서 같이 나가는 경우
                int sum = exitDp[r][c] + prisoner1Dp[r][c] + prisoner2Dp[r][c];
                if (board[r][c] == DOOR) sum -= 2;
                answer = Math.min(answer, sum);
            }
        }
        return answer;
    }

    private static List<int[]> findPrisoner(char[][] board) {
        List<int[]> result = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == PRISONER) {
                    result.add(new int[]{r, c});
                }
            }
        }
        return result;
    }

    public static int INF = 100_00_00;

    public static class Node {
        public int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static boolean isRange(int row, int col, char[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    public static void bfs(int r, int c, char[][] board, int[][] dp) {

        Deque<Node> queue = new LinkedList<>();
        queue.add(new Node(r, c));
        dp[r][c] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();

            int row = node.row;
            int col = node.col;

            for (int[] m : move) {
                int nr = row + m[0];
                int nc = col + m[1];

                if (isRange(nr, nc, board) && board[nr][nc] != WALL) {

                    if (board[nr][nc] == DOOR) {
                        if (dp[nr][nc] > dp[row][col] + 1) {
                            dp[nr][nc] = dp[row][col] + 1;
                            queue.addLast(new Node(nr, nc));
                        }
                    } else {
                        if (dp[nr][nc] > dp[row][col]) {
                            dp[nr][nc] = dp[row][col];
                            queue.addFirst(new Node(nr, nc));
                        }
                    }
                }
            }
        }
    }
}
