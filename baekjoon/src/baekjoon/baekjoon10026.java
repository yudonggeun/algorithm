package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon10026 {
	static char[][] picture;
	static boolean [][] isVisited;
	static final int [] mRow = {0, 0, -1, 1};
	static final int [] mCol = {1, -1, 0, 0};
	static int n;
	
	static int findSector(boolean special) {
		int count = 0;
		HashSet<Character> color = new HashSet<Character>();
		isVisited = new boolean[n][n];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(!isVisited[r][c]) {
					if(special && picture[r][c] != 'B') {
						color.add('R');
						color.add('G');
					}
					else {
						color.add(picture[r][c]);
					}
					dfs(r, c, color);
					count++;
					color.clear();
				}
			}
		}
		return count;
	}
	
	static void dfs(int row, int col, HashSet<Character> color) {
		if(row < 0 || col < 0 || row >= n || col >= n)
			return;
		if(!color.contains(picture[row][col]))
			return;
		if(isVisited[row][col])
			return;
		isVisited[row][col] = true;
		for(int i = 0; i< 4; i++) {
			int nr = row + mRow[i];
			int nc = col + mCol[i];
			dfs(nr, nc, color);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		picture = new char[n][];
		
		for(int i = 0; i< n; i++) {
			picture[i] = br.readLine().trim().toCharArray();
		}
		
		bw.write(findSector(false)+" "+findSector(true));
		bw.flush();
		bw.close();
		br.close();
	}
}