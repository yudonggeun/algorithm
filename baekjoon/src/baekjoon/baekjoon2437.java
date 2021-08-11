package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon2437 {
	static boolean isPossible(int[] num, int target) {
		for(int i = num.length-1; -1 < i; i--) {
			if(target >= num[i]) {
				target-=num[i];
				if(target == 0) {
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int num [] = new int [n];
		int i = 0;
		while(st.hasMoreTokens()) {
			num[i++] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		i=1;
		while(isPossible(num, i)) {
			i++;
		}
		bw.write(i+"");
		bw.flush();
		bw.close();
		br.close();
	}
}
