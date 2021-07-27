package baekjoon;

import java.io.*;

public class baekjoon1789 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long num = Long.parseLong(br.readLine());
		long n = 1;
		while(num >= n) {
			num -= n;
			
			n++;
		}
		bw.write(--n+"");
		bw.flush();
	}

}