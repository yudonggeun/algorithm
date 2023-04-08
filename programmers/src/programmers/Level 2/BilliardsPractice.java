//https://school.programmers.co.kr/learn/courses/30/lessons/169198
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {

    public int[] solution(int x, int y, int startX, int startY, int[][] balls) {
        List<Integer> answer = new LinkedList<>();

        int[] start = new int[]{startX, startY};

        for (int[] ball : balls) {
            int distance = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                distance = Math.min(distance, distance(start, ball));
                System.out.println(Arrays.toString(start) + ", " + Arrays.toString(ball) + " : " + distance(start, ball));
                start = rotation(start[0], start[1], i % 2 == 0 ? x : y);
                ball = rotation(ball[0], ball[1], i % 2 == 0 ? x : y);
            }
            System.out.println();
            answer.add(distance);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public int distance(int[] a, int[] b) {
        int l1 = Math.abs(a[0] - b[1]);
        int l2 = a[1] + b[1];
        if(l1 == 0 && a[1] >= b[1]) return Integer.MAX_VALUE;
        return l1 * l1 + l2 * l2;
    }

    public int[] rotation(int r, int c, int maxR) {
        return new int[]{c, maxR - r};
    }
}