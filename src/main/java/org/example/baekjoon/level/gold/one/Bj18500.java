package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.*;

public class Bj18500 {

    public static int row, col;
    public static boolean isLeft = true;
    public static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new char[row][];
        for (int i = 0; i < row; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        // simulation
        for (int i = 0; i < n; i++) {
            int line = row - Integer.parseInt(st.nextToken());
            simulation(line, board);
//            System.out.println("step : " + line);
//            print();
        }

        // print result
        print();
    }

    public static void print() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void simulation(int r, char[][] board) {

        int c = findMineral(r, board);
        if (c == -1) return;

        board[r][c] = '.';
        var visited = new boolean[row][col];

        List<Area> areaList = new ArrayList<>(4);
        for (int i = 0; i < move.length; i++) {

            int[] m = move[i];
            Area area = new Area();
            areaList.add(area);

            int nr = r + m[0];
            int nc = c + m[1];

            dfs(nr, nc, board, visited, area);
        }

        // 먼저 이동해야할 미네랄을 제거한다.
        areaList.forEach(Area::remove);

        // 점진적으로 0 ~ n 까지 내려간다.
        int down = 0;
        while (!areaList.isEmpty()) {
            for (Area area : areaList) {
                // 현재 이동을 해야하는 지를 판단
                for (Integer tc : area.points.keySet()) {
                    for (Integer tr : area.points.get(tc)) {
                        tr += down;
                        if (tr == row - 1 || board[tr + 1][tc] == 'x') {
                            area.move(down);
                            area.flag = true;
                            break;
                        }
                    }
//                    int tr = area.getMinimumRow(tc);
//                    if (tr == -1) continue;
                }

                if (area.flag) {
                    area.clear();
                }
            }

            down++;
            areaList.removeIf(area -> area.points.isEmpty());
        }
    }

    public static class Area {

        public boolean flag = false;
        public Map<Integer, TreeSet<Integer>> points = new HashMap<>();

        public void add(int r, int c) {
            points.putIfAbsent(c, new TreeSet<>());
            points.get(c).add(r);
        }

        public int getMinimumRow(int col) {
            if (points.containsKey(col)) {
                return points.get(col).last();
            }
            return -1;
        }

        public void move(int down) {
            for (Integer c : points.keySet()) {
                for (Integer r : points.get(c)) {
                    int tr = r + down;
                    board[tr][c] = 'x';
                }
            }
        }

        public void remove() {
            for (Integer c : points.keySet()) {
                for (Integer r : points.get(c)) {
                    board[r][c] = '.';
                }
            }
        }

        public void clear() {
            points.clear();
        }

    }

    static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},};

    public static void dfs(int r, int c, char[][] board, boolean[][] visited, Area points) {

        if (r < 0 || c < 0 || r >= row || c >= col) return;
        if (visited[r][c] || board[r][c] != 'x') return;

        visited[r][c] = true;
        points.add(r, c);

        for (int[] m : move) {

            int nr = r + m[0];
            int nc = c + m[1];

            dfs(nr, nc, board, visited, points);
        }
    }

    public static int findMineral(int line, char[][] board) {
        isLeft = !isLeft;
        if (isLeft) {
            for (int i = col - 1; i >= 0; i--) {
                if (board[line][i] == 'x') {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < col; i++) {
                if (board[line][i] == 'x') {
                    return i;
                }
            }
        }
        return -1;
    }

}
