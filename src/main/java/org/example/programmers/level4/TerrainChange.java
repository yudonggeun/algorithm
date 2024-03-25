package org.example.programmers.level4;
// https://school.programmers.co.kr/learn/courses/30/lessons/12984
public class TerrainChange {

    int p, q;

    public long solution(int[][] land, int p, int q) {
        this.p = p;
        this.q = q;
        int h = getHeight(land);
        long answer = getSize(land, h);
        return answer;
    }

    public int getHeight(int[][] land) {
        int start = 0;
        int end = 1_000_000_000;

        while (start < end) {

            int mid = (start + end) / 2;
            long midFrontCost = getSize(land, mid - 1);
            long midCost = getSize(land, mid);
            long midBackCost = getSize(land, mid + 1);

            if (midFrontCost < midCost && midCost < midBackCost) {
                end = mid - 1;
            } else if (midFrontCost > midCost && midCost > midBackCost) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return start;
    }

    public long getSize(int[][] land, int h) {
        if (h < 0 || h > 1_000_000_000) return Long.MAX_VALUE;
        long result = 0;
        for (int[] row : land) {
            for (int e : row) {
                long diff = e - h;
                if (diff > 0) {
                    result += diff * q;
                } else {
                    result += diff * -p;
                }
            }
        }
        return result;
    }
}