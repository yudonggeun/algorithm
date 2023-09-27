package org.example.baekjoon;
import java.io.*;

public class baekjoon10162 {
	static final int [] button = {300, 60, 10};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int [] click = {0, 0, 0};
		int time = Integer.parseInt(br.readLine());
		for(int i = 0; i< 3; i++) {
			while(button[i] <= time) {
				click[i]++;
				time -= button[i];
			}
		}
		if(time == 0) {
			for(int c : click) {
				bw.write(c+" ");
			}
		}
		else {
			bw.write("-1");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
