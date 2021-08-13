package baekjoon;
import java.io.*;

public class baekjoon2720 {
	public static final int coins [] = {25, 10, 5, 1}; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i< n ;i++) {
			int money = Integer.parseInt(br.readLine());
			for(int j = 0; j < 4; j++) {
				bw.write(money/coins[j]+" ");
				money %= coins[j];
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}