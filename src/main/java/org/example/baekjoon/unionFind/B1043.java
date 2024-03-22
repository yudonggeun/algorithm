package org.example.baekjoon.unionFind;

import java.io.*;
import java.util.*;

public class B1043 {

    static int[] person = new int[52];
    static int[] size = new int[52];

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (size[ra] > size[rb]) {
            person[ra] = rb;
            size[rb] += size[ra];
        } else {
            person[rb] = ra;
            size[ra] += size[rb];
        }
    }

    static int find(int t) {
        if (person[t] == t) return person[t];
        return person[t] = find(person[t]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());
        int totalPeople = Integer.parseInt(st.nextToken());
        int totalParty = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int totalTrue = Integer.parseInt(st.nextToken());

        Arrays.fill(size, 1);
        Arrays.setAll(person, i -> i);
        // true person union to 51
        while (st.hasMoreTokens()) {
            int person = Integer.parseInt(st.nextToken());
            union(person, 51);
        }

        // read party information
        int[][] parties = new int[totalParty][];

        for (int i = 0; i < totalParty; i++) {
            st = new StringTokenizer(br.readLine());
            int totalPartyPeople = Integer.parseInt(st.nextToken());

            int[] party = new int[totalPartyPeople];
            for (int j = 0; j < totalPartyPeople; j++) {
                party[j] = Integer.parseInt(st.nextToken());
            }
            parties[i] = party;
        }

        // union party
        for (int[] party : parties) {
            for (int j = 1; j < party.length; j++) {
                union(party[0], party[j]);
            }
        }

        // check party
        int count = 0;
        for (int[] party : parties) {
            for (int person : party) {
                int trueSet = find(51);
                int xSet = find(person);
                if (trueSet == xSet) {
                    count++;
                    break;
                }
            }
        }

        System.out.println(totalParty - count);
    }
}
