package baekjoon;
import java.io.*;

public class baekjoon14594 {
	public static int[] set;
	public static int find(int index) {
		if(index == set[index]) {
			return index;
		}
		return set[index] = find(set[index]);
	}
	public static void union(int x, int y) {
		int xP = find(x);
		int yP = find(y);
		
		if(xP != yP) {
			set[yP] = xP;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean [] isBreak = new boolean[N];
		set = new int[N];
		for(int i = 0; i< N; i++) {
			set[i] = i;
		}
		int count = 0;
		for(int i = 0; i< M; i++) {
			String [] input = br.readLine().split(" ");
			int left = Integer.parseInt(input[0])-1;
			int right = Integer.parseInt(input[1])-1;
			
			for(int j = left+1; j<= right; j++) {
				if(!isBreak[j]) {
					union(left, j);
					isBreak[j] = true;
					count++;
				}
			}
		}
		
		bw.write(N-count+"");
		bw.flush();
		bw.close();
		br.close();
	}
}