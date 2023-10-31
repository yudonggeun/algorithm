package org.example.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2475 {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var input = new StringTokenizer(br.readLine());

        int result = 0;
        while (input.hasMoreTokens()) {
            int num = Integer.parseInt(input.nextToken());
            result += (num * num);
            result %= 10;
        }
        System.out.println(result);
    }
}
