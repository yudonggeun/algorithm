package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/49994
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class VisitLength {
    public int solution(String dirs) {
        Player player = new Player();
        for (char c : dirs.toCharArray()) player.move(c);
        return paths.size() / 2;
    }

    Set<Integer> paths = new HashSet<>();

    class Player {
        int x, y;

        public void move(char command) {
            int nx = x, ny = y;
            if (command == 'U') ny++;
            else if (command == 'D') ny--;
            else if (command == 'R') nx++;
            else if (command == 'L') nx--;
            if (valid(nx, ny)) {
                paths.add(Objects.hash(x, y, nx, ny));
                paths.add(Objects.hash(nx, ny, x, y));
                x = nx;
                y = ny;
            }
        }
    }

    public boolean valid(int r, int c) {
        return -5 <= r && r <= 5 && -5 <= c && c <= 5;
    }
}