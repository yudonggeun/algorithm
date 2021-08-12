package baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon2847 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int [] level = new int[n];
		for(int i = 0; i< n; i++) {
			level[i] = Integer.parseInt(br.readLine());
		}
		int answer = 0;
		int up = level[n-1];
		for(int i = n-2; -1 < i; i--) {
			if(level[i] >= up) {
				answer += level[i] -up+1;
				level[i] -= level[i] -up+1;
			}
			up = level[i];
		}
		bw.write(answer+"");
		bw.close();
		br.close();
	}
}