package org.example.baekjoon.LIS;
//https://www.acmicpc.net/problem/2352

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2352 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[] lis = new int[size + 1];
        int pointer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            if (num > lis[pointer]) {
                lis[++pointer] = num;
            } else {
                insert(lis, num, pointer);
            }
        }
        System.out.println(pointer);
    }

    public static void insert(int[] list, int num, int pointer) {
        int start = 0;
        int end = pointer;

        while (start <= end) {
            int mid = (start + end) / 2;
            int value = list[mid];

            if (value < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        list[start] = num;
    }
}
