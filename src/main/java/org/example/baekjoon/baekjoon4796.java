package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon4796 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int i = 1;
		do {
			String [] input = br.readLine().split(" ");
			int l = Integer.parseInt(input[0]);
			int p = Integer.parseInt(input[1]);
			int v = Integer.parseInt(input[2]);
			if(l == 0 && p == 0 && v == 0) {
				break;
			}
			int answer = (v/p)*l;
			if(v%p > l) answer += l;
			else answer += v%p;
			bw.write("Case "+i+": "+ answer+"\n");
			i++;
		}while(true);
		bw.flush();
	}

}
