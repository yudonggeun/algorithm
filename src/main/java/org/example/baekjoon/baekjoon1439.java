package org.example.baekjoon;
import java.io.*;

public class baekjoon1439 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		int [] count = new int[2];
		for(int i = 0; i< input.length(); i++) {
			int num = input.charAt(i) - '0';
			if(i+1 == input.length()) {
				count[num]++;
			}
			else if(input.charAt(i) != input.charAt(i+1)) {
				count[num]++;
			}
		}
		int answer = Math.min(count[0], count[1]);
		bw.write(answer+"");
		bw.flush();
	}
}