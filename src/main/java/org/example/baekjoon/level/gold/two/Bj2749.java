package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj2749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        // init
        map.put(0L, 0L);
        map.put(1L, 1L);

        System.out.println(getFibonacci(n));
    }

    static Map<Long, Long> map = new HashMap<>();

    public static long getFibonacci(long n) {

        if (map.containsKey(n)) {
            return map.get(n);
        }

        if (n % 2 != 0) {
            n++;
            long fn = getFibonacci(n / 2);
            long fn1 = getFibonacci(n / 2 - 1);

            long result = (fn * fn + (fn1 * fn1)) % 1_000_000;
            map.put(n -1, result);
            return result;
        } else {
            long fn = getFibonacci(n / 2);
            long fn1 = getFibonacci(n / 2 - 1);

            long result = (fn * fn + (2 * fn * fn1)) % 1_000_000;
            map.put(n, result);
            return result;
        }
    }
}
