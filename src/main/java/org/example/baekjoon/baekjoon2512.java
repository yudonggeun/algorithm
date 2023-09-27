package org.example.baekjoon;
import java.io.*;

public class baekjoon2512 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		String [] input = br.readLine().split(" ");
		int max = Integer.parseInt(br.readLine());
		int budget [] = new int[n];
		int start = 0, end = 0;
		
		for(int i = 0; i< n; i++) {
			budget[i] = Integer.parseInt(input[i]);
			end = Math.max(end, budget[i]);
		}
		
		while(start <= end) {
			int mid = (start+end)/2;
			int sum = 0;
			for(int p : budget) {
				sum += (mid < p ? mid : p);
			}
			if(sum <= max) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		bw.write(end+"");
		bw.flush();
		
	}
}