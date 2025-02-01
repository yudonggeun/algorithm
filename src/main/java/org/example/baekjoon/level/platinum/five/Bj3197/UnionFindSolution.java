package org.example.baekjoon.level.platinum.five.Bj3197;

import java.io.*;
import java.util.*;

public class UnionFindSolution {

    public static int[] parent;

    public static void init() {
        int size = row * col;
        parent = new int[size];
        Arrays.setAll(parent, i -> i);
    }

    public static int find(int r, int c) {
        return find(index(r, c));
    }

    public static int find(int i) {
        if (parent[i] == i) return i;
        else return parent[i] = find(parent[i]);
    }

    public static void union(int ir, int ic, int jr, int jc) {
        union(index(ir, ic), index(jr, jc));
    }

    public static void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);

        if (ri == rj) return;
        parent[ri] = rj;
    }

    public static int row, col;

    public static int index(int r, int c) {
        return r * col + c;
    }

    public static boolean isRange(int r, int c) {
        return 0 <= r && r < row && c < col && 0 <= c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        init();
        char[][] board = new char[row][];

        for (int i = 0; i < row; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 분류하기
        List<int[]> waters = new LinkedList<>();
        List<int[]> birds = new LinkedList<>();
        List<int[]> ices = new LinkedList<>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                switch (board[r][c]) {
                    case 'L':
                        birds.add(new int[]{r, c});
                    case '.':
                        waters.add(new int[]{r, c});
                        break;
                    default:
                        ices.add(new int[]{r, c});
                }
            }
        }

        // 영역 초기화
        for (int[] water : waters) {
            for (int[] m : move) {
                int nr = water[0] + m[0];
                int nc = water[1] + m[1];

                if (isRange(nr, nc) && board[nr][nc] != 'X') {
                    union(nr, nc, water[0], water[1]);
                }
            }
        }

        // 시뮬레이션
        int count = 0;
        while (!isConnect(birds.get(0), birds.get(1))) {
            List<int[]> nextWaters = new LinkedList<>();
            for (int[] water : waters) {
                for (int[] m : move) {
                    int nr = water[0] + m[0];
                    int nc = water[1] + m[1];

                    if (!isRange(nr, nc)) continue;

                    if (board[nr][nc] == 'X') {
                        board[nr][nc] = '.';
                        union(nr, nc, water[0], water[1]);
                        for (int[] mm : move) {
                            int xnr = nr + mm[0];
                            int xnc = nc + mm[1];
                            if (isRange(xnr, xnc) && board[xnr][xnc] != 'X') {
                                union(xnr, xnc, nr, nc);
                            }
                        }
                        nextWaters.add(new int[]{nr, nc});
                    }
                }
            }

            waters = nextWaters;
            count++;
        }
        System.out.println(count);
    }

    public static boolean isConnect(int[] a, int[] b) {
        return find(a[0], a[1]) == find(b[0], b[1]);
    }

    public static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
}
