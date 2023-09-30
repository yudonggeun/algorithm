package org.example.baekjoon.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B18353 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int[] strengths = new int[size];
        List<Integer> lis = new ArrayList<>(size);
        int[] insertPoints = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());

        lis.add(0);
        int i = 0;
        while (st.hasMoreTokens()) {
            int strength = Integer.parseInt(st.nextToken()) * -1;
            int point = insertPoint(lis, strength);

            if (point == lis.size()) lis.add(strength);
            else lis.set(point, strength);

            strengths[i] = strength;
            insertPoints[i] = point;
            i++;
        }
        System.out.println(size - lis.size());
    }

    public static int insertPoint(List<Integer> lis, int num) {
        int point = Collections.binarySearch(lis, num);
        if (point < 0) point = Math.abs(point + 1);
        return point;
    }
}