package org.example.baekjoon;
import java.util.*;
import java.io.*;
class CabbagePatch{
	int row, col;
	boolean[][] cabbage;
	int subdivision;
	private int [] mRow = {0, 0, 1, -1};
	private int [] mCol = {1, -1, 0, 0};
	CabbagePatch(int row, int col){
		this.row = row; this.col = col;
		cabbage = new boolean[row][col];
		for(int i = 0; i< row; i++) Arrays.fill(cabbage[i], false);
		subdivision = 0;
	}
	public void add(int x, int y) {
		cabbage[y][x] = true;
	}
	public int count() {
		for(int i = 0; i < this.row; i++) {
			for(int j = 0; j< this.col; j++) {
				if(cabbage[i][j]) bfs(i, j);
			}
		}
		return subdivision;
	}
	public void bfs(int row, int col) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {row, col});
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			for(int i = 0; i< 4; i++) {
				int nRow = point[0] + mRow[i];
				int nCol = point[1] + mCol[i];
				if(!(0<= nRow && nRow < this.row && 0<= nCol && nCol < this.col))
					continue;
				if(!cabbage[nRow][nCol])
					continue;
				queue.add(new int[] {nRow, nCol});
				cabbage[nRow][nCol] = false;
			}
		}
		subdivision++;
	}
}
public class baekjoon1012 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int repeat = Integer.parseInt(br.readLine());
		for(int i = 0; i < repeat; i++) {
			String[] input = br.readLine().split(" ");
			int row = Integer.parseInt(input[1]);
			int col = Integer.parseInt(input[0]);
			int caseCount = Integer.parseInt(input[2]);
			CabbagePatch c = new CabbagePatch(row, col);
			for(int j = 0; j < caseCount; j++) {
				input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				c.add(x, y);
			}
			bw.write(c.count()+"\n");
		}
		bw.flush();
	}
}