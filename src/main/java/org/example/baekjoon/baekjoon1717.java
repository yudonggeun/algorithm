package org.example.baekjoon;
import java.io.*;

public class baekjoon1717 {
	static void init(int[] set) {
		for(int i = 0; i < set.length; i++) {
			set[i] = i;
		}
	}
	static int find(int[] set, int child) {
		return set[child] == child ? child : find(set, set[child]);
	}
	static void union(int[] set, int x, int y) {
		int xP = find(set, x);
		int yP = find(set, y);
		if(xP == yP) {
			return;
		}
		else if(xP > yP) {
			set[yP] = xP;
		}
		else {
			set[xP] = yP;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int [] set = new int[N+1];
		init(set);
		
		for(int i = 0; i< M ; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[1]);
			int y = Integer.parseInt(input[2]);
			if(input[0].equals("0")) {//union
				union(set, x, y);
			}
			else {//print
				bw.write(find(set, x) == find(set, y) ? "YES\n" : "NO\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}