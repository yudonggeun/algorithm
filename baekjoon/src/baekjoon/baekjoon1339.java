package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon1339 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		Hashtable<Character, Integer> hash = new Hashtable<Character, Integer>();
		char start = 'A';
		for(char i = 0; i < 26; i++) {
			hash.put((char) (start+i), 0);
		}
		for(int i = 0; i< n; i++) {
			String input = br.readLine();
			for(int j = 0; j < input.length(); j++) {
				int count = (int)Math.pow(10, input.length()-j-1);
				int value = hash.get(input.charAt(j));
				value += count;
				hash.replace(input.charAt(j), value);
			}
		}
		Object[] keySet = hash.keySet().toArray();
		Arrays.sort(keySet, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				return hash.get(arg1) - hash.get(arg0);
			}
		});
		int answer = 0;
		int num = 9;
		for(Object c : keySet) {
			answer += hash.get(c)*num;
			num--;
		}
		bw.write(answer+"");
		bw.flush();
		br.close();
		bw.close();
	}

}
