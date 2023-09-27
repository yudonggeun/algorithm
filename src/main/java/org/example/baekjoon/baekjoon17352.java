package org.example.baekjoon;
import java.io.*;

public class baekjoon17352 {
	static int find(int[] set, int index) {
		return set[index] == index ? index : find(set, set[index]);
	}
	static void union(int[] set, int x, int y) {
		int xParent = find(set, x);
		int yParent = find(set, y);
		
		if(xParent < yParent) {
			int tem = xParent;
			xParent = yParent;
			yParent = tem;
		}
		set[xParent] = yParent;		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int [] set = new int[n];
		for(int i = 0; i< n ; i++) {
			set[i] = i;
		}
		for(int i = 0; i< n-2; i++) {
			String [] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0])-1;
			int b = Integer.parseInt(input[1])-1;
			
			union(set, a, b);
		}
		
		n = find(set, 0);
		int [] result = new int[] {n, n};
		n = 0;
		while(result[0] == result[1]) {
			n++;
			result[1] = find(set, n);
		}
		bw.write((result[0]+1)+" "+(result[1]+1));
		bw.flush();
		bw.close();
		br.close();
	}
}