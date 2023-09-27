package org.example.baekjoon;
import java.util.*;
import java.io.*;

public class backjoon2812 {
	
	public static char[] getNum(LinkedList<Character> num, int delete) {
		Deque<Character> pront = new LinkedList<Character>();
		int goal = num.size() - delete;
		char[] result = new char[goal];
		if(num.size() == 1)
			return null;
		
		while(!num.isEmpty()) {
			char p = num.removeFirst();
			while(!pront.isEmpty() && delete > 0 && pront.peekLast() < p) {
				pront.removeLast();
				delete--;
			}
			pront.add(p);
		}
		
		for(int i = 0; i< goal; i++) {
			result[i] = pront.removeFirst();
		}
		
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		LinkedList<Character> num = new LinkedList<Character>();
		char [] numString = br.readLine().toCharArray();
		
		for(char n : numString) {
			num.add(n);
		}
		bw.write(getNum(num, K));
		bw.flush();
		bw.close();
		br.close();
	}
}