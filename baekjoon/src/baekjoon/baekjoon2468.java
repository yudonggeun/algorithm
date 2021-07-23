package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon2468 {
	static Queue<int[]> queue;
	static boolean[][] hash;
	static final int [] mRow = {0, 0, 1, -1};
	static final int [] mCol = {1, -1, 0, 0};
	static void bfs(int[][] area, int height) {
		while(!queue.isEmpty()) {
			int r = queue.peek()[0], c = queue.peek()[1];
			queue.poll();
			for(int i = 0; i< 4; i++) {
				int nr = r + mRow[i], nc = c + mCol[i];
				if(nr < 0 || nc < 0 | nr >= area.length || nc >= area.length)
					continue;
				if(area[nr][nc] <= height) 
					continue;
				if(hash[nr][nc])
					continue;
				queue.add(new int[] {nr, nc});
				hash[nr][nc] = true;
			}
		}
	}
	static void initHash() {
		for(int i = 0; i < hash.length; i++) {
			Arrays.fill(hash[i], false);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[][] area = new int[n][n];
		int height = 0, max = 0;
		for(int i = 0; i< n; i++) {
			String [] input = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				area[i][j] = Integer.parseInt(input[j]);
				height = Math.max(height, area[i][j]);
			}
		}
		//
		hash = new boolean[n][n];
		for(int i = 0; i<= height; i++) {
			queue = new LinkedList<int[]>();
			initHash();
			int count = 0;
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					if(i < area[r][c] && !hash[r][c]) {
						queue.add(new int[] {r, c});
						hash[r][c] = true;
						bfs(area, i);
						count++;
					}
				}
				max = Math.max(max, count);
			}
		}
		bw.write(max+"");
		bw.flush();
	}
}
