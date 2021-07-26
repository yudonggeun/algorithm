package baekjoon;
import java.io.*;
public class baekjoon5585 {
	static final int [] coins = {500, 100, 50, 10, 5, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int price = Integer.parseInt(br.readLine());
		int change = 1000 - price;
		int count = 0;
		for(int i = 0; i< coins.length; i++) {
			if(coins[i] == 1) {
				count+= change;
				break;
			}
			while(change >= coins[i]) {
				count++;
				change -= coins[i];
			}
		}
		bw.write(count+"");
		bw.flush();
	}

}
