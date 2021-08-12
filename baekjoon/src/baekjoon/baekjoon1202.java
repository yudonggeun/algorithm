package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1202 {
	static final int m = 0, v = 1;
	public static int findJewel(int[][] jewel, int backpack) {
		int answer;
		for(int i = 0 ; i< jewel.length; i++) {
			if(jewel[i][v] == -1) {
				continue;
			}
			if(jewel[i][m] <= backpack) {
				answer = jewel[i][v];
				jewel[i][v] = -1;
				return answer;
			}
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		PriorityQueue<int[]> jewel = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if(arg0[v] == arg1[v])
					return arg1[m]-arg0[m];
				return arg1[v] - arg0[v];
			}
		});
		for(int i = 0; i< n; i++) {
			input = br.readLine().split(" ");
			int [] information = new int[] {
					Integer.parseInt(input[m]),
					Integer.parseInt(input[v])
			};
			jewel.add(information);
		}
		Integer [] backpack = new Integer[k];
		for(int i = 0; i< k ;i++) {
			backpack[i] = Integer.parseInt(br.readLine().trim());
		}
		//
		Arrays.sort(backpack, new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg1-arg0;
			}
		});
		//가방의 무게에 담을 수 있는 보석 중에서 가장 가치가 있는 보석 찾기
		long answer = 0;
		for(int size : backpack) {
			int [] jewelry;
			do {
				if(jewel.size() == 0) {
					jewelry = new int[] {0, 0};
					break;				
					}
				jewelry = jewel.poll();
				System.out.println(jewelry[v]+","+jewelry[m]+"");
			}while(jewelry[m] > size);
			if(jewelry[m] == 0) break;
			answer += jewelry[v];
		}
		bw.write(answer+"");
		bw.flush();
	}
}