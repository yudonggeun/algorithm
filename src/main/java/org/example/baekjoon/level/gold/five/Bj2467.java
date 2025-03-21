package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.*;

public class Bj2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(input);

        int left = 0;
        int right = n - 1;

        int abs = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (left < right) {
            int sum = input[left] + input[right];
            if (abs > Math.abs(sum)) {
                answer[0] = input[left];
                answer[1] = input[right];
                abs = Math.abs(sum);
            }
            if (sum == 0) {
                break;
            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
