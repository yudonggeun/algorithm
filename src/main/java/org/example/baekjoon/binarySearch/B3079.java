package org.example.baekjoon.binarySearch;

import java.io.*;
import java.util.*;

public class B3079 {

    static int[] timeToProcess;
    static long MAX_TIME = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());
        int workerCount = Integer.parseInt(st.nextToken());
        int touristCount = Integer.parseInt(st.nextToken());

        timeToProcess = new int[workerCount];
        for (int i = 0; i < workerCount; i++) {
            timeToProcess[i] = Integer.parseInt(br.readLine());
        }

        long minimumTime = 0;
        long maximumTime = MAX_TIME;

        while (minimumTime <= maximumTime) {
            long time = (minimumTime + maximumTime) / 2;
            if (haveEnoughTime(time, touristCount)) {
                maximumTime = time - 1;
            } else {
                minimumTime = time + 1;
            }
        }
        System.out.println(minimumTime);
    }

    private static boolean haveEnoughTime(long time, int touristCount) {
        long totalCapability = 0;
        for (int tk : timeToProcess) {
            long capability = time / tk;
            totalCapability += capability;
            totalCapability = Math.min(totalCapability, MAX_TIME);
        }
        return totalCapability >= touristCount;
    }
}
