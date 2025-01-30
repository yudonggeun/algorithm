package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.*;

public class Bj14503 {

    static int[][] board;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;

    static class Robot {
        public int r, c, direction;

        public boolean isCleanAround(int[][] board) {
            int dirtyCount = 0;
            for (int[] m : move) {
                if (isRange(r + m[0], c + m[1], board) &&
                        board[r + m[0]][c + m[1]] == 0
                ) {
                    dirtyCount++;
                }
            }
            return dirtyCount == 0;
        }

        public boolean rotationAndMove(int[][] board) {
            direction = nextDirection(this.direction);
            int nr = this.r + move[direction][0];
            int nc = this.c + move[direction][1];

            if (isRange(nr, nc, board) && board[nr][nc] == 0) {
                this.r = nr;
                this.c = nc;
                return true;
            }
            return false;
        }

        public boolean moveBack(int[][] board) {
            int backDirection = (this.direction + 2) % 4;
            int nr = this.r + move[backDirection][0];
            int nc = this.c + move[backDirection][1];
            if (isRange(nr, nc, board) && board[nr][nc] != 1) {
                this.r = nr;
                this.c = nc;
                return true;
            }
            return false;
        }
    }

    public static int nextDirection(int direction) {
        return (direction + 3) % 4;
    }

    public static void main(String[] args) throws IOException {

        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Robot robot = new Robot();
        robot.r = Integer.parseInt(st.nextToken());
        robot.c = Integer.parseInt(st.nextToken());
        robot.direction = Integer.parseInt(st.nextToken());

        board = new int[n][];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // logic
        boolean finFlag = false;
        int moveCount = 0;
        while (!finFlag) {
            // step 1
            if (board[robot.r][robot.c] == 0) {
                moveCount++;
                board[robot.r][robot.c] = 2;
                continue;
            }

            if (robot.isCleanAround(board)) { // step 2
                if (robot.moveBack(board)) { // 2.1
                } else { // 2.2
                    finFlag = true;
                }
            } else { // step 3
                while (!robot.rotationAndMove(board)){
                }
            }
        }
        // output
        System.out.println(moveCount);
    }

    public static boolean isRange(int r, int c, int[][] board) {
        return 0 <= r && 0 <= c && r < board.length && c < board[0].length;
    }
}
