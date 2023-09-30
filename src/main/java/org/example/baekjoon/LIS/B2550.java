package org.example.baekjoon.LIS;
// https://www.acmicpc.net/problem/2550

import java.io.IOException;
import java.util.*;

public class B2550 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        int[] start = new int[size];
        int[] end = new int[size];
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt() - 1;
            start[i] = numbers[i];
        }

        for (int i = 0; i < size; i++) {
            end[sc.nextInt() - 1] = i;
        }

        for (int i = 0; i < size; i++) {
            numbers[i] = end[numbers[i]];
        }

        List<Integer> lisSample = lisSample(numbers);
        System.out.println(lisSample.size());
        lisSample.stream()
                .mapToInt(i -> start[i] + 1)
                .sorted()
                .forEach(num -> System.out.print(num + " "));
    }

    public static List<Integer> lisSample(int[] numbers) {
        int size = numbers.length;
        List<Integer> lis = new ArrayList<>(size);
        int[] points = new int[size];

        for (int i = 0; i < numbers.length; i++) {

            int point = insertPoint(lis, numbers[i]);
            if (point == lis.size()) lis.add(numbers[i]);
            else lis.set(point, numbers[i]);

            points[i] = point;
        }

        LinkedList<Integer> stack = new LinkedList<>();

        int index = lis.size() - 1;
        for (int i = size - 1; i > -1; i--) {
            if (index == points[i]) {
                stack.addFirst(i);
                index--;
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