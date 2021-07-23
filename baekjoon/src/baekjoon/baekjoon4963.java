package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon4963 {
	static Queue<int[]> queue;
	static final int[] mRow = {-1, -1, -1,  0, 0,  1, 1, 1};
	static final int[] mCol = {-1,  0,  1, -1, 1, -1, 0, 1};
	static void bfs(int [][] earth) {
		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.peek()[1];
			queue.poll();
			for(int i = 0; i< 8; i++) {
				int nR = r+mRow[i], nC = c+mCol[i];
				if(nR < 0 || nC < 0 || nR >= earth.length || nC >= earth[0].length)
					continue;
				if(earth[nR][nC] == 0)
					continue;
				queue.add(new int[] {nR, nC});
				earth[nR][nC] = 0;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[1]);
		int col = Integer.parseInt(input[0]);
		int [][] earth;
		while(row != 0 && col != 0) {
			int answer = 0;
			earth = new int[row][col];
			for(int i = 0; i< row; i++) {
				input = br.readLine().split(" ");
				for(int j = 0; j< col; j++) {
					earth[i][j] = Integer.parseInt(input[j]);
				}
			}
			queue = new LinkedList<int[]>();
			for(int i = 0; i< row; i++) {
				for(int j = 0; j< col; j++) {
					if(earth[i][j] == 1) {
						queue.add(new int[] {i, j});
						earth[i][j] = 0;
						bfs(earth);
						answer++;
					}
				}
			}
			bw.write(answer+"\n");
			input = br.readLine().split(" ");
			row = Integer.parseInt(input[1]);
			col = Integer.parseInt(input[0]);
		}
		bw.flush();
	}

}
