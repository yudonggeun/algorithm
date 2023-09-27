package org.example.baekjoon;
import java.io.*;

public class baekjoon1080 {
	public static void change(boolean[][] a, int row, int col) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				a[row+i][col+j] ^= true;
			}
		}
	}
	public static boolean isEqual(boolean[][] a, boolean[][] b) {
		if(a.length != b.length || a[0].length != b[0].length) {
			return false;
		}
		for(int i = 0; i< a.length; i++) {
			for(int j = 0; j< a[0].length; j++) {
				if(a[i][j] != b[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		boolean [][] a = new boolean[n][m], b = new boolean[n][m];
		for(int i = 0; i< n; i++) {
			String line = br.readLine();
			for(int j = 0; j< m; j++) {
				a[i][j] = (line.charAt(j) == '0' ? false : true);
			}
		}
		for(int i = 0; i< n; i++) {
			String line = br.readLine();
			for(int j = 0; j< m; j++) {
				b[i][j] = (line.charAt(j) == '0' ? false : true);
			}
		}
		//
		int count = 0;
		for(int i = 0; i< n-2; i++) {
			for(int j = 0; j< m-2; j++) {
				if(a[i][j] != b[i][j]) {
					change(a, i, j);
					count++;
				}
			}
		}
		if(isEqual(a, b)) {
			bw.write(count+"");
		}
		else {
			bw.write("-1");
		}
		bw.flush();
	}
}