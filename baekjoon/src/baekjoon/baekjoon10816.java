package baekjoon;
import java.util.*;
import java.io.*;
//숫자카드2
public class baekjoon10816 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
		String [] input = br.readLine().split(" ");
		for(int i = 0; i< n; i++) {
			if(hash.containsKey(input[i])) {
				hash.replace(input[i], hash.get(input[i])+1);
			}
			else {
				hash.put(input[i], 1);
			}
		}
		int m = Integer.parseInt(br.readLine());
		input = br.readLine().split(" ");
		for(int i = 0; i< m ; i++) {
			if(hash.containsKey(input[i])) {
				bw.write(hash.get(input[i])+" ");
			}
			else {
				bw.write("0 ");
			}
		}
		bw.flush();
		
	}

}
