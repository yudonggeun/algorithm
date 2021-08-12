package baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon16953 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int count = 1;
		while(b > a) {
			if(b%2 == 1) {
				if(b%10 != 1) {
					b = 0;
				}
				else {
					b = b/10;
				}
			}
			else {
				b = b/2;
			}
			count++;
		}
		
		if(b == a) {
			bw.write(count+"");
		}
		else {
			bw.write("-1");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
