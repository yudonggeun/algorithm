package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon2110 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int [] house = new int [n];
		int c = Integer.parseInt(input[1]);
		for(int i = 0; i < n; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		
		int left = 1, right = house[n-1];
		while(left <= right) {
			int mid = (left+right)/2;
			int node = 1;
			int preHouse = house[0];
			for(int h : house) {
				if(h-preHouse >= mid) {
					node++;
					preHouse = h;
				}
			}
			if(node >= c) {
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		bw.write(right+"");		
		bw.flush();
	}

}
