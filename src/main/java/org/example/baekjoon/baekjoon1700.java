package org.example.baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon1700 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int size = Integer.parseInt(input[0]);
		int use = Integer.parseInt(input[1]);
		input = br.readLine().split(" ");
		
		int count = 0;
		HashSet<String> isUsed = new HashSet<String>();
		
		for(int i = 0; i < use; i++) {
			if(isUsed.size() < size) {
				isUsed.add(input[i]);
				continue;
			}
			if(isUsed.contains(input[i])) {
				continue;
			}
			HashSet<String> tem = new HashSet<String>();
			for(int j = i; j < use; j++) {
				if(tem.size() == size-1) {
					break;
				}
				if(isUsed.contains(input[j])) {
					tem.add(input[j]);
				}
			}
			for(String num : isUsed) {
				if(!tem.contains(num)) {
					isUsed.remove(num);
					isUsed.add(input[i]);
					count++;
					break;
				}
			}
		}
		
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();
	}
}