package org.example.programmers.Level3;
// https://school.programmers.co.kr/learn/courses/30/lessons/1833

import java.util.*;

public class Camping {
    public int solution(int n, int[][] data) {
        // x 축 정렬
        var map = new TreeMap<Integer, List<int[]>>();
        int maxX = 0;
        for (int[] d : data) {
            map.putIfAbsent(d[0], new LinkedList<>());
            map.get(d[0]).add(d);
        }
        // x 축 압축
        for (var key : map.keySet()) {
            List<int[]> values = map.get(key);
            for (int[] value : values) {
                value[0] = maxX;
            }
            maxX++;
        }
        // y 축 정렬
        map.clear();
        int maxY = 0;
        for (int[] d : data) {
            map.putIfAbsent(d[1], new LinkedList<>());
            map.get(d[1]).add(d);
        }
        // y 축 압축
        for (var key : map.keySet()) {
            var values = map.get(key);
            for (int[] value : values) {
                value[1] = maxY;
            }
            maxY++;
        }

        // init camping
        int[][] camping = new int[maxX][maxY];
        for (int[] d : data) {
            int x = d[0];
            int y = d[1];
            camping[x][y]++;
        }

        // calculate accumulated sum
        for(int r = 0; r < maxX -1; r++){
            for(int c = 0; c < maxY -1; c++){
                camping[r + 1][c] += camping[r][c];
            }
        }
        for (int c = 0; c < maxY - 1; c++) {
            for (int r = 0; r < maxX - 1; r++) {
                camping[r][c + 1] += camping[r][c];
            }
        }

        int count = 0;
        // check

        for (int a = 0; a < data.length; a++) {
            for (int b = a + 1; b < data.length; b++) {
                if (isValid(data[a], data[b], camping)) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean isValid(int[] a, int[] b, int[][] sum) {

        if (a[0] == b[0] || a[1] == b[1]) return false;
        int minX = Math.min(a[0], b[0]);
        int minY = Math.min(a[1], b[1]);
        int maxX = Math.max(a[0], b[0]) - 1;
        int maxY = Math.max(a[1], b[1]) - 1;

        int areaA = sum[minX][minY];
        int areaB = sum[minX][maxY];
        int areaC = sum[maxX][minY];
        int areaD = sum[maxX][maxY];

        int total = areaD + areaA - areaB - areaC;
        return total == 0;
    }
}
