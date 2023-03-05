//https://school.programmers.co.kr/learn/courses/30/lessons/159993
import java.util.*;

class Solution {

    Node start, end, lever;
    String[] maps;
    Set<Node> wall = new HashSet<>();

    public int solution(String[] maps) {
        init(maps);
        if(bfs(start, lever) == -1 || bfs(lever, end) == -1) return -1;
        return end.cost;
    }

    public void init(String[] maps) {
        this.maps = maps;
        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[row].length(); col++) {
                if (maps[row].charAt(col) == 'S') start = new Node(row, col, 0);
                else if (maps[row].charAt(col) == 'E') end = new Node(row, col, 0);
                else if (maps[row].charAt(col) == 'L') lever = new Node(row, col, 0);
                else if (maps[row].charAt(col) == 'X') wall.add(new Node(row, col, 0));
            }
        }
    }

    public boolean isRange(Node node) {
        return node.r > -1 && node.r < maps.length && node.c > -1 && node.c < maps[0].length();
    }

    public int bfs(Node from, Node to) {

        int[][] move = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visit = new HashSet<>(wall);
        queue.add(from);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.r == to.r && node.c == to.c) {
                to.cost = node.cost;
                return node.cost;
            }

            for (int[] m : move) {
                int nr = node.r + m[0];
                int nc = node.c + m[1];
                int cost = node.cost + 1;

                Node next = new Node(nr, nc, cost);
                if(isRange(next) && !visit.contains(next)){
                    visit.add(next);
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    class Node {
        int r, c, cost;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return r == node.r && c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}