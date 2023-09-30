package org.example.baekjoon.LIS;
// https://www.acmicpc.net/submit/13711

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B13711 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int size = Integer.parseInt(br.readLine());

        int[] case1 = readInput();
        int[] case2 = readInput();
        int[] reverseCase2 = new int[size];

        for (int i = 0; i < case2.length; i++) {
            reverseCase2[case2[i]] = i;
        }

        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = reverseCase2[case1[i]];
        }

        System.out.println(size - notContain(numbers).size());
    }

    public static int[] readInput() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s) - 1)
                .toArray();
    }

    public static Set<Integer> notContain(int[] numbers) {
        int size = numbers.length;
        List<Integer> lis = new ArrayList<>(size);
        int[] points = new int[size];

        for (int i = 0; i < numbers.length; i++) {

            int point = insertPoint(lis, numbers[i]);
            if (point == lis.size()) lis.add(numbers[i]);
            else lis.set(point, numbers[i]);

            points[i] = point;
        }

        Set<Integer> stack = new HashSet<>();

        int index = lis.size() - 1;
        for (int i = size - 1; i > -1; i--) {
            if (index == points[i]) {
                index--;
            } else {
                stack.add(i);
            }
        }

        return stack;
    }

    public static int insertPoint(List<Integer> lis, int num) {
        int point = Collections.binarySearch(lis, num);
        if (point < 0) point = Math.abs(point + 1);
        return point;
    }

}
