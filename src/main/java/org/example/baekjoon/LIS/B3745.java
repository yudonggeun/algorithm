package org.example.baekjoon.LIS;
// https://www.acmicpc.net/problem/3745

import java.io.IOException;
import java.util.*;

public class B3745 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int size = sc.nextInt();
            int[] stockPrices = new int[size];
            for(int i = 0; i < stockPrices.length; i++){
                stockPrices[i] = sc.nextInt();
            }

            System.out.println(lisCount(stockPrices));
        }
    }

    public static int lisCount(int[] numbers) {
        int size = numbers.length;
        List<Integer> lis = new ArrayList<>(size);

        for (int i = 0; i < numbers.length; i++) {

            int point = insertPoint(lis, numbers[i]);

            if (point == lis.size()) lis.add(numbers[i]);
            else lis.set(point, numbers[i]);
        }
        return lis.size();
    }

    public static int insertPoint(List<Integer> lis, int num) {
        int point = Collections.binarySearch(lis, num);
        if (point < 0) point = Math.abs(point + 1);
        return point;
    }
}
