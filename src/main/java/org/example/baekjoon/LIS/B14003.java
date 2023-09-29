package org.example.baekjoon.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>(n + 1);
        int[] numbers = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            int point = Collections.binarySearch(list, numbers[i]);

            if (point < 0) {
                point = Math.abs(point + 1);
            }
            if (point == list.size()) {
                list.add(numbers[i]);
            } else {
                list.set(point, numbers[i]);
            }
            dp[i] = point;
        }

        int length = list.size() - 1;
        System.out.println(list.size());

        LinkedList<Integer> answer = new LinkedList<>();
        for (int i = n - 1; i > -1; i--) {
            if (length == dp[i]) {
                answer.push(numbers[i]);
                length--;
            }
        }

        for (int num : answer) {
            System.out.print(num + " ");
        }
    }
}
