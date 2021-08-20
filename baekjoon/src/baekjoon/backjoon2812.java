package baekjoon;
import java.util.*;
import java.io.*;

public class backjoon2812 {
	
	public static char[] oneMinus(LinkedList<Character> num, int delete) {
		Stack<Character> pront = new Stack<Character>();
		int goal = num.size() - delete;
		char[] result = new char[num.size()-delete];
		if(num.size() == 1)
			return null;
		
		while(pront.size() < goal) {
			if(delete == 0) {
				while(!num.isEmpty()) {
					pront.push(num.removeFirst());
				}
				break;
			}
			if(pront.isEmpty()) {
				pront.push(num.removeFirst());
			}
			if(pront.peek() >= num.getFirst()) {
				pront.push(num.removeFirst());
			}
			else if(pront.peek() < num.getFirst()) {
				pront.pop();
				delete--;
			}
		}
		
		for(int i = pront.size()-1 ; i> -1; i--) {
			result[i] = pront.pop();
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
		bw.write(oneMinus(num, K));
		bw.flush();
		bw.close();
		br.close();
	}
}