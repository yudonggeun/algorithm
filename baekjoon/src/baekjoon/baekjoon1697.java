package baekjoon;
import java.util.*;
import java.io.*;
public class baekjoon1697 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int subin = Integer.parseInt(input[0]);
		int brother = Integer.parseInt(input[1]);
		int answer = 0;
		
		Queue<int []> queue = new LinkedList<int []>();
		HashSet<Integer> hash = new HashSet<Integer>();
		
		hash.add(subin);
		queue.add(new int[] {subin, 0});
		if(subin == brother) {
			bw.write(answer+"");
			bw.flush();
			return;
		}
		find : while(!queue.isEmpty()) {
			int[] p = queue.poll();
			for(int i = 0; i< 3; i++) {
				int [] next = new int[] {p[0], p[1]+1};
				if(i == 0)
					next[0]++;
				else if(i == 1)
					next[0]--;
				else
					next[0] *= 2;
				if(next[0] == brother) {
					answer = next[1];
					break find;
				}
				if(next[0] < 0 || next[0] > 100_000) continue;
				if(!hash.contains(next[0])){
					hash.add(next[0]);
					queue.add(next);
				}
			}
		}
		bw.write(answer+"");
		bw.flush();
	}

}
