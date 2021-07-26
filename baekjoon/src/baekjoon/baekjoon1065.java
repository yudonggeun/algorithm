package baekjoon;
import java.util.*;
import java.io.*;

public class baekjoon1065 {
	static boolean isCase(int n) {
		if(n < 100 && 0 < n) return true;
		int num = n%10;
		n = n/10;
		int next = n%10;
		n = n/10;
		int gap = next - num;
		do {
			num = next;
			next = n%10; n = n/10;
			if(gap != next -num) {
				return false;
			}
		}while(n != 0);
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int count = 0;
		for(int i = 1; i <= n; i++) {
			if(isCase(i)) count++;
		}
		bw.write(count+"");
		bw.flush();
	}

}
