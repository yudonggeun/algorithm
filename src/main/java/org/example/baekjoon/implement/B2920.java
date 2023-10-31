package org.example.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2920 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var ascending = "1 2 3 4 5 6 7 8";
        var descending = "8 7 6 5 4 3 2 1";

        var input = br.readLine().trim();

        if (input.equals(ascending)) {
            System.out.println("ascending");
        } else if (input.equals(descending)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}