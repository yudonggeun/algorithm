package baekjoon;
import java.util.*;
import java.io.*;
public class baekjoon7569 {
	static final int [] mRow = {0, 0, 0, 0, 1, -1};
	static final int [] mCol = {0, 0, 1, -1, 0, 0};
	static final int [] mHeight = {1, -1, 0, 0, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int raw = 0;
		int row = Integer.parseInt(input[1]);
		int col = Integer.parseInt(input[0]);
		int hei = Integer.parseInt(input[2]);
		int [][][] box = new int[hei][row][col];
		boolean [][][] isVisit = new boolean[hei][row][col];
		Queue<int[]> queue = new LinkedList<int[]>();
		
		for(int k = 0; k < hei ; k++) {
			for(int i = 0; i < row; i++) {
				input = br.readLine().trim().split(" ");
				for(int j = 0; j < col; j++) {
					box[k][i][j] = Integer.parseInt(input[j]);
					if(box[k][i][j] == 1) {
						queue.add(new int[] {k, i, j, 0});
						isVisit[k][i][j] = true;
					}
					else if(box[k][i][j] == 0) {
						raw++;
					}
				}
			}
		}
		//
		int day = 0;
		while(!queue.isEmpty()) {
			int h = queue.peek()[0];
			int r = queue.peek()[1];
			int c = queue.peek()[2];
			day = queue.peek()[3];
			queue.poll();
			for(int m = 0; m < 6; m++) {
				int nh = h + mHeight[m];
				int nr = r + mRow[m];
				int nc = c + mCol[m];
				if(nh < 0 || nh >= hei || nr < 0 || nr >= row || nc < 0 || nc >= col)
					continue;
				if(isVisit[nh][nr][nc])
					continue;
				if(box[nh][nr][nc] != 0)
					continue;
				raw--;
				isVisit[nh][nr][nc] = true;
				queue.add(new int[] {nh, nr, nc, day+1});
				box[nh][nr][nc] = 1;
			}
		}
		//
		if(raw == 0) bw.write(day+"");
		else bw.write("-1");
		bw.flush();
		
	}

}
