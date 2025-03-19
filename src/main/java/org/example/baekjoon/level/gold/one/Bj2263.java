package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.*;

public class Bj2263 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] inorder, postorder;
    static Map<Integer, Integer> inorderIndex, postorderIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        inorderIndex = new HashMap<>();
        postorderIndex = new HashMap<>();

        for (int index = 0; index < n; index++) {
            inorderIndex.put(inorder[index], index);
            postorderIndex.put(postorder[index], index);
        }

        printRoot(n - 1, 0, n - 1);
        bw.flush();
    }

    public static void printRoot(int r, int from, int to) throws IOException {

        bw.write(postorder[r] + " ");

        int inorderRootIndex = inorderIndex.get(postorder[r]);

        int leftTreeSize = inorderRootIndex - from;
        int rightTreeSize = to - inorderRootIndex;

        if (leftTreeSize > 0) {
            printRoot(r - rightTreeSize - 1, from, inorderRootIndex - 1);
        }

        if (rightTreeSize > 0) {
            printRoot(r - 1, inorderRootIndex + 1, to);
        }
    }
}
