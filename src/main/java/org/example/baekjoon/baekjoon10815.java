package org.example.baekjoon;
import java.util.*;
import java.io.*;
//����ī��
public class baekjoon10815 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		HashSet<String> hash = new HashSet<String>();
		String [] input = br.readLine().split(" ");
		for(int i = 0; i< n; i++) {
			hash.add(input[i]);
		}
		int m = Integer.parseInt(br.readLine());
		input = br.readLine().split(" ");
		for(int i = 0; i< m ; i++) {
			if(hash.contains(input[i])) {
				bw.write("1 ");
			}
			else {
				bw.write("0 ");
			}
		}
		bw.flush();
		
	}

}
