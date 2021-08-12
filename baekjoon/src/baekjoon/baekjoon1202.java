package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1202 {
	static final int m = 0, v = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg1[v] - arg0[v];//가치 내림차순
			}
		});
		int [][] jewel = new int [n][];
		int [] backpack = new int[k];
		for(int i = 0; i< n; i++) {
			input = br.readLine().split(" ");
			jewel[i] = new int[] {
					Integer.parseInt(input[m]),
					Integer.parseInt(input[v])
			};
		}
		for(int i = 0; i< k ;i++) {
			backpack[i] = Integer.parseInt(br.readLine().trim());
		}
		//
		Arrays.sort(jewel, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[m] == b[m]) {
					return b[v]-a[v];
				}
				return a[m] - b[m];
			}
		});
		Arrays.sort(backpack);
		//
		long answer = 0;
		int index = 0;
		for(int size : backpack) {
			for(; index < jewel.length; index++) {
				if(jewel[index][m] > size) {
					break;
				}
				queue.add(jewel[index]);
			}
			if(!queue.isEmpty()) {
				answer += queue.poll()[v];
			}
		}
		bw.write(answer+"");
		bw.flush();
	}
}