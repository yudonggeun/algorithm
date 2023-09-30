package org.example.baekjoon.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class B11568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(lisCount(numbers));
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
