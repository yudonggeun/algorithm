package org.example.baekjoon.level.gold.four;

import java.io.*;
import java.util.*;

public class Bj1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = input[0];
        int sum = input[1];

        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // accumulate the calculation.
        for (int i = 1; i < data.length; i++) {
            data[i] += data[i - 1];
        }

        int start = 1;
        int end = size;

        while (start <= end) {
            int len = (start + end) / 2;
            if (isValid(len, size, sum, data)) {
                end = len - 1;
            } else {
                start = len + 1;
            }
        }

        if (isValid(start, size, sum, data)) {
            System.out.println(start);
        } else {
            System.out.println(0);
        }
    }

    public static boolean isValid(int len, int size, int sum, int[] data) {
        for (int from = 0; from + len - 1 < size; from++) {
            int target = getSum(from, from + len - 1, data);
            if (target >= sum) {
                return true;
            }
        }
        return false;
    }

    public static int getSum(int from, int to, int[] accumulatedSums) {
        if (from == 0) {
            return accumulatedSums[to];
        }
        return accumulatedSums[to] - accumulatedSums[from - 1];
    }
}
