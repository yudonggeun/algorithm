package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bj1038 {

    static long[] base = {1_000_000_000, 100_000_000, 10_000_000, 1_000_000, 100_000, 10_000, 1_000, 100, 10, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (long l : base) {
            getNum(9, l, 0);
        }
        int index = numbers.size() - 1 - n;
        if (0 <= index && index < numbers.size()) {
            System.out.println(numbers.get(numbers.size() - 1 - n));
        } else {
            System.out.println(-1);
       }
    }

    public static List<Long> numbers = new ArrayList<>();

    public static void getNum(long i, long base, long sum) {
        if (base == 0) {
            numbers.add(sum);
            return;
        }

        long nextBase = base / 10;
        for (long a = i; a >= 0; a--) {
            getNum(a - 1, nextBase, sum + a * base);
        }
    }
}
