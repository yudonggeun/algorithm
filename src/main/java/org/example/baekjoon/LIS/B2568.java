package org.example.baekjoon.LIS;
//https://www.acmicpc.net/problem/2568

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B2568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = 500_001;
        int[] numbers = new int[size];

        int inputSize = Integer.parseInt(br.readLine());

        for (int i = 0; i < inputSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            numbers[from] = to;
        }

        int[] lis = new int[size];
        int[] insertPoint = new int[size];
        int pointer = 0;

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int point = lis[pointer] < num ? ++pointer : insertPoint(lis, num, pointer);

            lis[point] = num;
            insertPoint[i] = point;
        }

        int index = pointer;

        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = numbers.length - 1; i > -1; i--) {
            if (insertPoint[i] == 0) continue;

            if (index == insertPoint[i]) {
                index--;
            } else {
                stack.push(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        System.out.println(inputSize - pointer);
        while (!stack.isEmpty()) {
            sb.append(stack.pop())
                    .append("\n");
        }

        System.out.println(sb);
    }

    private static int insertPoint(int[] lis, int num, int end) {
        int start = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int value = lis[mid];

            if (value < num) start = mid + 1;
            else end = mid - 1;
        }
        return start;
    }
}
