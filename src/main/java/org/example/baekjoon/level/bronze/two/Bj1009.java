package org.example.baekjoon.level.bronze.two;

import java.io.*;
import java.util.Arrays;

public class Bj1009 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int line = Integer.parseInt(br.readLine());

        for (int i = 0; i < line; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int a = input[0];
            int b = input[1];

            int answer = getMod(a, b, 10);

            if (answer == 0) answer = 10;
            System.out.println(answer);
        }
    }

    public static int getMod(int a, int b, int mod) {
        if (b == 0) return 1;
        else if (b == 1) return a % mod;

        int half = getMod(a, b / 2, mod);

        if (b % 2 == 0) {
            return half * half % mod;
        } else {
            return half * half * a % mod;
        }
    }
}
