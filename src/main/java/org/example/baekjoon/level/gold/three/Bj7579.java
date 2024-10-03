package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj7579 {
        public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                int[] input = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

                int n = input[0];
                targetMemory = input[1];

                int[] datas = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

                int[] costs = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

                int[][] memorys = new int[n+1][n * 100 + 1];

                for (int i = 0; i < n; i++) {
                        for (int c = 0; c < n * 100 + 1; c++) {
                                memorys[i + 1][c] = Math.max(memorys[i][c], getMemory(memorys, i, c - costs[i], datas[i]));
                        }
                }

                for(int i = 0; i < n * 100 + 1; i++){
                        if(memorys[n][i] >= targetMemory){
                                System.out.println(i);
                                break;
                        }
                }
        }

        public static int getMemory(int[][] memorys, int index, int cost, int add){
                if(index < 0 || index >= memorys.length){
                        return 0;
                }
                if(cost < 0 || cost >= memorys[index].length){
                        return 0;
                }
                return memorys[index][cost] + add;
        }

        public static int targetMemory = 0;
}
