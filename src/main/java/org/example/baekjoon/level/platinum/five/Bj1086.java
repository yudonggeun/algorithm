package org.example.baekjoon.level.platinum.five;

import java.io.*;
import java.util.*;

public class Bj1086 {

    static int k;
    static int[] tens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = new String[n];
        for (int i = 0; i < n; i++) input[i] = br.readLine();
        Number[] numbers = new Number[n];
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) numbers[i] = new Number(input[i]);
        tens = new int[54];
        tens[0] = 1;
        for (int i = 1; i < 54; i++) {
            tens[i] = tens[i - 1] * 10 % k;
        }

        // calculate
        long[][] dp = new long[1 << n][];
        dp[0] = new long[k];
        dp[0][0] = 1L;

        Queue<Integer> bits = new LinkedList<>();
        bits.add(0);

        while (!bits.isEmpty()) {
            int bit = bits.poll();

            for (int i = 0; i < n; i++) {
                int p = 1 << i;
                int nextBit = bit | p;

                if (dp[nextBit] == null) {
                    dp[nextBit] = new long[k];
                    bits.add(nextBit);
                }
                if ((bit & p) == 0) {
                    for (int j = 0; j < k; j++) {
                        int remainder = ((j * tens[numbers[i].data.length()]) % k + numbers[i].remainder) % k;
                        dp[nextBit][remainder] += dp[bit][j];
                    }
                }
            }
        }

        long[] last = dp[(1 << n) - 1];
        long possible = last[0];
        long total = Arrays.stream(last).sum();

        if (possible == 0) {
            System.out.println("0/1");
        } else {
            long gcd = gcd(possible, total);
            possible /= gcd;
            total /= gcd;
            System.out.println(possible + "/" + total);
        }
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static class Number {
        String data;
        int remainder;

        public Number(String input) {
            this.data = input;
            int target = 0;
            for (int i = 0; i < data.length(); i++) {
                int number = data.charAt(i) - '0';
                target *= 10;
                target += number;
                target %= k;
            }
            this.remainder = target;
        }
    }
}
