package org.example.baekjoon;
import java.io.*;

public class baekjoon2805 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		long pre = 0, target=0, back = 0;
		long [] woods = new long[N];
		input = br.readLine().split(" ");
		for(int i = 0; i< N ;i++) {
			woods[i] = Integer.parseInt(input[i]);
			back = Math.max(back, woods[i]);
		}
		//		
		while(pre <= back) {
			target = (pre+back)/2;
			long len = 0;
			for(long wood : woods) {
				if(wood > target) {
					len += wood-target;
				}
			}
			if(len < M) {
				back = target-1;
			}
			else {
				pre = target+1;
			}
		}
		bw.write(back+"");
		
		bw.flush();
	}

}
