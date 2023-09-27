package org.example.baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon1783 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		int answer = col-2;
		if(row == 1) {
			answer = 1;
		}
		if(row == 2) {
			if(col < 7) {
				answer = (col-1)/2+1;
			}
			else{
				answer = 4;
			}
		}
		else {
			if(col < 5) {
				answer = col;
			}
			else if(col == 5) {
				answer = 4;
			}
		}
		bw.write(answer+"");
		bw.flush();
		br.close();
		bw.close();
	}
}