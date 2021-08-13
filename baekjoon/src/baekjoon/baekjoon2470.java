package baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon2470 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		var list = new ArrayList<Integer>();
		
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		int left = 0, right = list.size()-1;
		int p=0, b=0, gap = Integer.MAX_VALUE;
		while(left < right) {
			int sum = list.get(left)+list.get(right);
			int abs = Math.abs(sum);
			if(gap > abs) {
				gap = abs;
				p = list.get(left);
				b = list.get(right);				
			}
			if(sum < 0) {
				left++;
			}
			else if(sum > 0) {
				right--;
			}
			else {
				break;
			}
		}
		bw.write(p+" "+b);
		bw.flush();
		bw.close();
		br.close();
	}
}