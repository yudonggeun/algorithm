package org.example.baekjoon;
import java.io.*;

public class baekjoon1976 {
	static int [] city;
	static int find(int index) {
		return city[index] == index ? index : find(city[index]);
	}
	static void union(int x, int y) {
		int pX = find(x);
		int pY = find(y);
		city[pX] = pY;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cityCount = Integer.parseInt(br.readLine());
		int plan = Integer.parseInt(br.readLine());
		city = new int[cityCount];
		for(int i = 0; i< cityCount; i++) {
			city[i] = i;
		}
		
		for(int i = 0; i< cityCount; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < cityCount; j++) {
				if(input[j].equals("1")) {
					union(i, j);
				}
			}
		}
		
		String [] input = br.readLine().split(" ");
		int parent = find(Integer.parseInt(input[0])-1);
		boolean isPossible = true;
		
		for(int i = 1; i< plan; i++) {
			if(parent != find(Integer.parseInt(input[i])-1)) {
				isPossible = false;
				break;
			}
		}
		
		bw.write((isPossible ? "YES" : "NO"));
		bw.flush();
		bw.close();
		br.close();
	}
}