package org.example.baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1715 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		for(int i = 0; i< n ;i++) {
			queue.add(Long.parseLong(br.readLine()));
		}
		Long answer = 0l;
		while(queue.size() != 1) {
			Long next = queue.poll()+queue.poll();
			answer += next;
			queue.add(next);
		}
		bw.write(answer+"");
		bw.flush();
		br.close();
		bw.close();
	}

}
