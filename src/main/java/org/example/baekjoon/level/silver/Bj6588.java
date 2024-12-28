package org.example.baekjoon.level.silver;

import java.io.*;

public class Bj6588 {

    static boolean[] isNotPrime = new boolean[1_000_001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        isNotPrime[1] = true;
        isNotPrime[0] = true;
        for (int i = 2; i <= Math.sqrt(1_000_000); i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= 1_000_000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        int n = Integer.parseInt(br.readLine());
        while (n != 0) {
            boolean flag = true;
            for (int a = 3; a <= n; a += 2) {
                if (!isNotPrime[a] && !isNotPrime[n - a]) {
                    bw.write(String.format("%d = %d + %d", n, a, n - a));
                    bw.newLine();
                    flag = false;
                    break;
                }
            }
            if (flag) {
                bw.write("Goldbach's conjecture is wrong.");
            }
            n = Integer.parseInt(br.readLine());
        }
        bw.flush();
    }
}
