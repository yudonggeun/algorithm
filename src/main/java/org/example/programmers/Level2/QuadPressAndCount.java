package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/68936
class QuadPressAndCount {
    int one = 0, zero = 0;

    public int[] solution(int[][] arr) {
        dfs(arr, 0, 0, arr.length, arr[0].length);
        return new int[]{zero, one};
    }

    public void dfs(int[][] arr, int sr, int sc, int rLen, int cLen) {
        if (isAllSame(arr, sr, sc, rLen, cLen)) {
            if (arr[sr][sc] == 1) one++;
            else zero++;
        } else {
            int nextRLen = rLen / 2;
            int nextCLen = cLen / 2;
            dfs(arr, sr, sc, nextRLen, nextCLen);
            dfs(arr, sr + nextRLen, sc, nextRLen, nextCLen);
            dfs(arr, sr, sc + nextCLen, nextRLen, nextCLen);
            dfs(arr, sr + nextRLen, sc + nextCLen, nextRLen, nextCLen);
        }
    }

    public boolean isAllSame(int[][] arr, int sr, int sc, int rLen, int cLen) {
        int common = arr[sr][sc];
        for (int r = sr; r < sr + rLen; r++) {
            for (int c = sc; c < sc + cLen; c++) {
                if (common != arr[r][c]) return false;
            }
        }
        return true;
    }
}