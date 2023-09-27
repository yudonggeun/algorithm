package org.example.baekjoon;
import java.io.*;

public class baekjoon1300 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int start = 1, end= n*n;
		while(start <= end) {
			int mid = (start+end)/2;
			int cnt = 0;
			for(int i = 1; i<=n; i++) {
				cnt+= Math.min(mid/i, n);
			}
			if(k > cnt) {
				start = mid+1;
			}
			else{
				end = mid-1;
			}
		}
		
		bw.write(end+1+"");
		bw.flush();
	}
}