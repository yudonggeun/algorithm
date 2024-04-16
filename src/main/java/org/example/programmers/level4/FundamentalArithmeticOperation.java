package org.example.programmers.level4;
// https://school.programmers.co.kr/learn/courses/30/lessons/1843

public class FundamentalArithmeticOperation {

    public int solution(String[] arr) {
        int size = (arr.length - 1) / 2 + 1;
        // to int array
        int[] numbers = new int[size];
        numbers[0] = Integer.parseInt(arr[0]);
        int idx = 0;
        for (int i = 1; i < arr.length; i += 2) {
            int num = Integer.parseInt(arr[i + 1]);
            if (arr[i].equals("-")) {
                numbers[++idx] = -num;
            } else numbers[++idx] = num;
        }
        // init
        dpMax = new int[size][size];
        dpMin = new int[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (r == c) {
                    dpMin[r][c] = numbers[r];
                    dpMax[r][c] = numbers[r];
                    continue;
                }
                dpMax[r][c] = Integer.MIN_VALUE;
                dpMin[r][c] = Integer.MAX_VALUE;
            }
        }

        return compute(0, size - 1, numbers, true);
    }

    public int[][] dpMax;
    public int[][] dpMin;

    /**
     * @return (start, end) 범위에 대해서  isMax가 참이라면 최대값을 반환하고, 거짓이라면 최소값을 반환한다.
     */

    public int compute(int start, int end, int[] numbers, boolean isMax) {
        boolean isMinus = numbers[start] < 0;
        if (isMinus) {
            numbers[start] = -numbers[start];
        }
        if (start == end) {
            int result = numbers[start];
            if (isMinus) numbers[start] = -numbers[start];
            dpMax[start][end] = result;
            dpMin[start][end] = result;
            return result;
        }
        if (start + 1 == end) {
            int result = numbers[start] + numbers[end];
            if (isMinus) numbers[start] = -numbers[start];
            dpMax[start][end] = result;
            dpMin[start][end] = result;
            return result;
        }

        if (isMax && dpMax[start][end] != Integer.MIN_VALUE) {
            if (isMinus) numbers[start] = -numbers[start];
            return dpMax[start][end];
        }
        if (!isMax && dpMin[start][end] != Integer.MAX_VALUE) {
            if (isMinus) numbers[start] = -numbers[start];
            return dpMin[start][end];
        }

        for (int stopover = start; stopover < end; stopover++) {
            int max, min;
            if (numbers[stopover + 1] < 0) {
                max = compute(start, stopover, numbers, true) - compute(stopover + 1, end, numbers, false);
                min = compute(start, stopover, numbers, false) - compute(stopover + 1, end, numbers, true);
            } else {
                max = compute(start, stopover, numbers, true) + compute(stopover + 1, end, numbers, true);
                min = compute(start, stopover, numbers, false) + compute(stopover + 1, end, numbers, false);
            }
            dpMax[start][end] = Math.max(dpMax[start][end], max);
            dpMin[start][end] = Math.min(dpMin[start][end], min);
        }

        if (isMinus) {
            numbers[start] = -numbers[start];
        }
        return isMax ? dpMax[start][end] : dpMin[start][end];
    }
}