package org.example.baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon11000 {
	public static final int start = 0, end = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		var array = new ArrayList<int[]>();
		var hash = new Hashtable<Integer, Integer>();
		String [] input;
		for(int i = 0; i< n; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			if(hash.containsKey(start)) {
				hash.replace(start, hash.get(start)+1);
			}
			else {
				hash.put(start, 1);
			}
			if(hash.containsKey(end)) {
				hash.replace(end, hash.get(end)-1);
			}
			else {
				hash.put(end, -1);
			}
		}
		//
		
		int count = 0;
		int room = 0;
		var keySet = hash.keySet().toArray();
		Arrays.sort(keySet);
		for(Object time : keySet) {
			room += hash.get(time);
			count = Math.max(room, count);
		}
		
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();
	}
}