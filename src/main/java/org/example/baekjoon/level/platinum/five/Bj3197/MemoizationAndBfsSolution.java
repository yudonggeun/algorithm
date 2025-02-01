package org.example.baekjoon.level.platinum.five.Bj3197;

import java.io.*;
import java.util.*;

public class MemoizationAndBfsSolution {

    public static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        char[][] board = new char[row][];

        for (int i = 0; i < row; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 분류하기
        Queue<int[]> waters = new LinkedList<>();
        List<int[]> birds = new LinkedList<>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                switch (board[r][c]) {
                    case 'L':
                        birds.add(new int[]{r, c});
                    case '.':
                        waters.add(new int[]{r, c});
                        break;
                }
            }
        }

        // 초기화 : 처음 단계의 호수는 0단계만에 갈 수 있다.
        int[][] dp = new int[row][col];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int[] water : waters) {
            dp[water[0]][water[1]] = 0;
        }

        // 얼음이 몇단계에 녹는지 기록
        while (!waters.isEmpty()) {
            List<int[]> nextWater = new LinkedList<>();
            for (int[] water : waters) {
                for (int[] m : move) {
                    int nr = water[0] + m[0];
                    int nc = water[1] + m[1];
                    if (isRange(nr, nc) && dp[nr][nc] > dp[water[0]][water[1]] + 1) {
                        nextWater.add(new int[]{nr, nc});
                        dp[nr][nc] = dp[water[0]][water[1]] + 1;
                    }
                }
            }
            waters.clear();
            waters.addAll(nextWater);
        }

        // 최소 단계를 거치는 경로를 탐색
        boolean[][] isVisited = new boolean[row][col];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.step));
        int[] start = birds.get(0);
        int[] end = birds.get(1);
        pq.add(new Node(start[0], start[1], 0));
        isVisited[start[0]][start[1]] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.r == end[0] && node.c == end[1]) {
                System.out.println(node.step);
                break;
            }

            for (int[] m : move) {
                int nr = node.r + m[0];
                int nc = node.c + m[1];

                if (isRange(nr, nc) && !isVisited[nr][nc]) {
                    int nStep = Math.max(node.step, dp[nr][nc]);
                    isVisited[nr][nc] = true;
                    pq.add(new Node(nr, nc, nStep));
                }
            }
        }
    }

    static class Node {
        int r, c;
        int step;

        public Node(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }

    public static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean isRange(int r, int c) {
        return 0 <= r && r < row && c < col && 0 <= c;
    }
}
