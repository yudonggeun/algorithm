package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon2206 {
	static final int [] mRow = {0, 0, 1, -1};
	static final int [] mCol = {1, -1, 0, 0};
	static int bfs(char[][] map, int[][][] isVisited) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0, 1, 0});
		isVisited[0][0][0] = 1; isVisited[0][0][1] = 1;
		while(!queue.isEmpty()) {
			int row = queue.peek()[0];
			int col = queue.peek()[1];
			int count = queue.peek()[2];
			int drill = queue.peek()[3];//지금까지 부순 벽의 수
			queue.poll();
			if(row == map.length-1 && col == map[0].length-1)
				return count;
			for(int i = 0; i< 4; i++) {
				int nr = row + mRow[i];
				int nc = col + mCol[i];
				int nCount = count+1;
				int ndrill = drill;
				if(nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length)
					continue;				
				if(map[nr][nc] == '1') {
					if(drill == 0){
						ndrill++;
					}
					else {
						continue;
					}
				}
				if(isVisited[nr][nc][ndrill] == 1)
					continue;
				isVisited[nr][nc][ndrill] = 1;
				queue.add(new int[] {nr, nc, nCount, ndrill});
			}
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[0]);
		int col = Integer.parseInt(input[1]);
		char [][] map = new char[row][];
		int [][][] isVisited = new int[row][col][2];
		for(int i = 0; i< row; i++) {
			map[i] = br.readLine().trim().toCharArray();
		}
		bw.write(bfs(map, isVisited)+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
