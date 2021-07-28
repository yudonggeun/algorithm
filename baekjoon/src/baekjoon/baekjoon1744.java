package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1744 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minus = new PriorityQueue<Integer>();
		PriorityQueue<Integer> plus = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i = 0; i< n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num <= 0) {
				minus.add(num);
			}
			else {
				plus.add(num);
			}
		}
		
		int answer = 0;
		while(plus.size() > 1) {
			int a = plus.poll();
			int b = plus.poll();
			if(b==1) {
				answer += a+b;
			}
			else {
				answer += a*b;
			}
		}
		if(!plus.isEmpty())
			answer += plus.poll();
		
		while(minus.size() > 1) {
			answer += minus.poll()*minus.poll();
		}
		if(!minus.isEmpty())
			answer += minus.poll();
		bw.write(answer+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
