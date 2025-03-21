package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(input);

        long abs = Long.MAX_VALUE;
        long[] candidates = null;

        for (int s = 0; s < input.length - 2; s++) {
            for (int e = s + 2; e < input.length; e++) {
                int start = s + 1;
                int end = e - 1;
                long target = -(input[s] + input[e]);

                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (input[mid] == target) {
                        System.out.println(String.format("%d %d %d", input[s], input[mid], input[e]));
                        return;
                    } else if (input[mid] < target) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }

                long value = Math.abs(input[s] + input[e] + input[start]);
                if (value < abs && start != e) {
                    abs = value;
                    candidates = new long[]{input[s], input[start], input[e]};
                }
                value = Math.abs(input[s] + input[e] + input[end]);
                if (value < abs && end != s) {
                    abs = value;
                    candidates = new long[]{input[s], input[end], input[e]};
                }
            }
        }

        System.out.println(String.format("%d %d %d", candidates[0], candidates[1], candidates[2]));
    }
}
