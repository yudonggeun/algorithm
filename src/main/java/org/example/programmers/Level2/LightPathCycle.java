package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/86052
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class LightPathCycle  {
    public int[] solution(String[] grid) {
        List<Integer> answer = new LinkedList<>();
        init(grid);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 0; k < 4; k++) {
                    int count = 0;
                    int row = i;
                    int col = j;
                    int direction = k;
                    while (!isVisit(row, col, direction)){
                        count++;
                        visit[row][col][direction] = true;
                        direction = getDirection(direction, map[row][col]);
                        int[] next = getNext(row, col, direction);
                        row = next[0];
                        col = next[1];
                    }
                    if(count > 0) answer.add(count);
                }
            }
        }
        Collections.sort(answer);
        return answer.stream().mapToInt(i -> i).toArray();
    }

    void init(String[] gird) {
        map = new char[gird.length][];
        for (int i = 0; i < gird.length; i++) {
            map[i] = gird[i].toCharArray();
        }
        visit = new boolean[map.length][map[0].length][4];
    }

    char[][] map;
    boolean[][][] visit;
    boolean isVisit(int row, int col, int direction) {
        return visit[row][col][direction];
    }

    int[] getNext(int row, int col, int direction) {
        if (direction == 1) col++;
        else if (direction == 2) row++;
        else if (direction == 3) col--;
        else row--;
        return getRangeIndex(row, col);
    }

    private int getDirection(int direction, char type) {
        if (type == 'L') direction = (direction + 3) % 4;
        if (type == 'R') direction = (direction + 1) % 4;
        return direction;
    }

    private int[] getRangeIndex(int row, int col) {
        if (row < 0) row = map.length - 1;
        else if (row >= map.length) row = 0;
        if (col < 0) col = map[0].length - 1;
        else if (col >= map[0].length) col = 0;
        return new int[]{row, col};
    }
}