package baekjoon;
import java.util.*;
import java.io.*;
public class baekjoon3109 {
	static final char x = 'x';
	static int [] mRow = {-1, 0, 1};
	static int row = 0, col = 0;
	static int count = 0;
	
	public static boolean isValid(int r, int c) {
		return r > -1 && r < row && c > -1 && c < col;
	}
	public static int findRoad(char[][] map, int pRow, int pCol) {
		if(pCol == col-1) {
			count++;
			return 1;
		}
		for(int i = 0; i< mRow.length; i++) {
			int nRow = pRow + mRow[i];
			int nCol = pCol+1;
			
			if(isValid(nRow, nCol) && map[nRow][nCol] != x) {
				map[nRow][nCol] = x;
				if(findRoad(map, nRow, nCol) == 1) {
					return 1;
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		while(st.hasMoreTokens()) {
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
		}
		char[][] map = new char[row][col];
		
		for(int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
		}
		//
		for(int i = 0; i< row; i++) {
			findRoad(map, i, 0);
		}
		//
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();		
	}
}