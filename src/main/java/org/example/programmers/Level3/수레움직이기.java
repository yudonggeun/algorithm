package org.example.programmers.Level3;

public class 수레움직이기 {
    public int solution(int[][] maze) {
        init(maze);
        boolean[][] redVisit = new boolean[maze.length][maze[0].length];
        boolean[][] blueVisit = new boolean[maze.length][maze[0].length];

        redVisit[srx][sry] = true;
        blueVisit[sbx][sby] = true;
        // red first move
        moveRed(srx, sry, sbx, sby, maze, 0, redVisit, blueVisit);

        // blue first move
        moveBlue(srx, sry, sbx, sby, maze, 0, redVisit, blueVisit);

        if (minTurn == Integer.MAX_VALUE) return 0;
        return minTurn / 2 + minTurn % 2;
    }

    int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},};
    int frx, fry, fbx, fby;
    int srx, sry, sbx, sby;
    int minTurn = Integer.MAX_VALUE;

    public void moveRed(int rx, int ry, int bx, int by, int[][] maze, int turn, boolean[][] redVisit, boolean[][] blueVisit) {
        if (minTurn < turn) return;
        if (frx == rx && fry == ry) {
            if (fbx == bx && fby == by) {
                minTurn = Math.min(minTurn, turn);
                return;
            }
            moveBlue(rx, ry, bx, by, maze, turn + 1, redVisit, blueVisit);
            return;
        }

        for (int[] m : move) {
            int nx = rx + m[0];
            int ny = ry + m[1];

            if (!isRange(nx, ny, maze) || redVisit[nx][ny] || maze[nx][ny] == 5 || (nx == bx && ny == by)) {
                continue;
            }
            redVisit[nx][ny] = true;
            moveBlue(nx, ny, bx, by, maze, turn + 1, redVisit, blueVisit);
            redVisit[nx][ny] = false;
        }
    }

    public void moveBlue(int rx, int ry, int bx, int by, int[][] maze, int turn, boolean[][] redVisit, boolean[][] blueVisit) {
        if (minTurn < turn) return;
        if (fbx == bx && fby == by) {
            if (frx == rx && fry == ry) {
                minTurn = Math.min(minTurn, turn);
                return;
            }
            moveRed(rx, ry, bx, by, maze, turn + 1, redVisit, blueVisit);
            return;
        }

        for (int[] m : move) {
            int nx = bx + m[0];
            int ny = by + m[1];

            if (!isRange(nx, ny, maze) || blueVisit[nx][ny] || maze[nx][ny] == 5 || (nx == rx && ny == ry)) {
                continue;
            }
            blueVisit[nx][ny] = true;
            moveRed(rx, ry, nx, ny, maze, turn + 1, redVisit, blueVisit);
            blueVisit[nx][ny] = false;
        }
    }

    public boolean isRange(int x, int y, int[][] maze) {
        return -1 < x && x < maze.length && -1 < y && y < maze[0].length;
    }

    public void init(int[][] maze) {
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                switch (maze[x][y]) {
                    case 1 -> {
                        srx = x;
                        sry = y;
                    }
                    case 2 -> {
                        sbx = x;
                        sby = y;
                    }
                    case 3 -> {
                        frx = x;
                        fry = y;
                    }
                    case 4 -> {
                        fbx = x;
                        fby = y;
                    }
                }
            }
        }
    }

}