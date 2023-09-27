//https://school.programmers.co.kr/learn/courses/30/lessons/60063
import java.util.*;

class Solution {
    public int solution(int[][] board) {
        this.board = board;
        return bfs();
    }

    public int bfs() {

        Point a = new Point(0, 0), b = new Point(0, 1);
        Robot r = new Robot(a, b, 0);

        Queue<Robot> queue = new LinkedList<>();
        visit.add(r);
        queue.add(r);

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();

            if (robot.isEnd()) return robot.count;

            Set<Robot> nextRobots = nextRobots(robot);
            visit.addAll(nextRobots);
            queue.addAll(nextRobots);
        }
        return 0;
    }

    Set<Robot> visit = new HashSet<>();

    public Set<Robot> nextRobots(Robot robot) {
        Set<Robot> result = new HashSet<>();

        for (int i = 0; i < move.length; i++) {
            Robot next = robot.move(i);
            if (next != null && !visit.contains(next)) {
                result.add(next);
            }
        }

        for (int direction = 0; direction < 2; direction++) {
            for (int type = 0; type < 2; type++) {
                Robot next = robot.rotation(direction, type);
                if (next != null && !visit.contains(next)) {
                    result.add(next);
                }
            }
        }
        return result;
    }

    class Robot {
        Point a, b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return Objects.equals(a, robot.a) && Objects.equals(b, robot.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        int count;

        public Robot(Point a, Point b, int count) {
            this.a = a;
            this.b = b;
            this.count = count;
        }

        public Robot move(int direction) {
            Point na = a.move(direction), nb = b.move(direction);
            if (na == null || nb == null) return null;
            return new Robot(na, nb, this.count + 1);
        }

        public Robot rotation(int direction, int type) {
            Point na = a, nb = b;
            if (type == 0) nb = na.rotation(nb, direction);
            else na = nb.rotation(na, direction);
            if (na == null || nb == null) return null;
            return new Robot(na, nb, this.count + 1);
        }

        public boolean isEnd() {
            return a.isEnd() || b.isEnd();
        }
    }

    int[][] board;
    int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean isZero(int r, int c) {
        return 0 <= r && r < board.length && 0 <= c && c < board[0].length && board[r][c] == 0;
    }

    class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        public boolean isEnd() {
            int n = board.length - 1;
            return r == n && c == n;
        }

        public Point move(int direction) {
            int nr = r + move[direction][0];
            int nc = c + move[direction][1];
            if (isZero(nr, nc)) return new Point(nr, nc);
            return null;
        }

        int[][][][] target = {
                {
                        {
                                {-1, 0}, {-1, -1}
                        },
                        {
                                {+1, 0}, {+1, +1}
                        },
                        {
                                {0, +1}, {-1, +1},
                        },
                        {
                                {0, -1}, {+1, -1},
                        }
                },
                {
                        {
                                {+1, 0}, {+1, -1},
                        },
                        {
                                {-1, 0}, {-1, +1},
                        },
                        {
                                {0, -1}, {-1, -1},
                        },
                        {
                                {0, +1}, {+1, +1}
                        },
                }
        };

        public Point rotation(Point p, int direction) {
            int location = 0;
            if (r == p.r && c - 1 == p.c) location = 0;
            else if (r == p.r && c - 1 != p.c) location = 1;
            else if (r - 1 == p.r) location = 2;
            else location = 3;

            int[] movePoint = target[direction][location][0];
            int[] pathPoint = target[direction][location][1];

            if (isZero(r + movePoint[0], c + movePoint[1])
                    && isZero(r + pathPoint[0], c + pathPoint[1]))
                return new Point(r + movePoint[0], c + movePoint[1]);

            return null;
        }
    }
}