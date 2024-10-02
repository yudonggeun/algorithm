package org.example.baekjoon.level.gold.four;

import java.io.*;
import java.util.*;

public class Bj1253 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        long[] data = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        
        int[] marking = new int[size + 1];
        int[] edgeCase = new int[size];
        Arrays.fill(marking, 0);
        
        Arrays.sort(data);

        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                long sum = data[i] + data[j];

                int firstIndex = getFirstIndex(data, sum);
                int lastIndex = getLastIndex(data, sum);

                marking[firstIndex]++;
                marking[lastIndex + 1]--;

                if(firstIndex <= i && i <= lastIndex){
                    edgeCase[i]++;
                }
                if(firstIndex <= j && j <= lastIndex){
                    edgeCase[j]++;
                }
            }
        }

        for(int i = 1; i < size + 1; i++){
            marking[i] += marking[i - 1];
        }

        for(int i = 0; i < size; i++){
            marking[i] -= edgeCase[i];
        }

        int answer = 0;
        for(int i = 0; i < size; i++){
            if(marking[i] > 0){
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static int getFirstIndex(long[] data, long sum){
        int s = 0;
        int e = data.length-1;

        while(s <= e){
            int m = (s + e) /2;

            if(data[m] >= sum){
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return s;
    }

    public static int getLastIndex(long[] data, long sum){
        int s = 0;
        int e = data.length -1;

        while(s <= e){
            int m = (s + e) /2;

            if(data[m] <= sum){
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return e;
    }
}
