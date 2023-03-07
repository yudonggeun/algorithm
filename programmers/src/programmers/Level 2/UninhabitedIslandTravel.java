//https://school.programmers.co.kr/learn/courses/30/lessons/154540
import java.util.*;

class Solution {

    int[] size, parent;
    boolean[][] visit;
    String[] maps;

    public int[] solution(String[] maps) {

        //초기화
        size = new int[maps.length * maps[0].length()];
        parent = new int[maps.length * maps[0].length()];
        visit = new boolean[maps.length][maps[0].length()];
        this.maps = maps;
        Arrays.setAll(parent, index -> index);

        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[0].length(); col++) {
                int index = getIndex(row, col);
                if (maps[row].charAt(col) == 'X') {
                    size[index] = 0;
                    visit[row][col] = true;
                } else {
                    size[index] = maps[row].charAt(col) - '0';
                }
            }
        }

        //dfs를 통한 union작업 실행
        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[0].length(); col++) {
                if (visit[row][col]) continue;
                visit[row][col] = true;
                dfs(row, col);
            }
        }

        //결과 추출
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < size.length; i++) {
            if (parent[i] == i && size[i] > 0) {
                result.add(size[i]);
            }
        }
        Collections.sort(result);

        return result.isEmpty() ? new int[]{-1} : result.stream().mapToInt(a -> a).toArray();
    }

    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void dfs(int row, int col) {
        for (int[] m : move) {
            int nr = row + m[0], nc = col + m[1];

            if (isRange(nr, nc) && !visit[nr][nc]) {
                union(getIndex(row, col), getIndex(nr, nc));
                visit[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }

    public boolean isRange(int row, int col) {
        return row > -1 && row < maps.length && col > -1 && col < maps[0].length();
    }

    public int getIndex(int row, int col) {
        return (row * maps[0].length()) + col;
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;
        if (size[rootA] > size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    public int find(int index) {
        if (parent[index] == index) return parent[index];
        else return parent[index] = find(parent[index]);
    }
}