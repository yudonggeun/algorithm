package org.example.baekjoon.LIS;
//https://www.acmicpc.net/problem/1365

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1365 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int[] numbers = new int[size];
        List<Integer> lis = new ArrayList<>(size);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int i = 0;
        while(st.hasMoreTokens()){
            numbers[i] = Integer.parseInt(st.nextToken());
            int point = insertPoint(lis, numbers[i]);

            if(point == lis.size()) lis.add(numbers[i]);
            else lis.set(point, numbers[i]);

            i++;
        }

        System.out.println(size - lis.size());
    }

    public static int insertPoint(List<Integer> lis, int num){
        int point = Collections.binarySearch(lis, num);
        if(point < 0) point = Math.abs(point + 1);
        return point;
    }
}
