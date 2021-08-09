package baekjoon;
import java.io.*;

public class baekjoon1072 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int x = Integer.parseInt(input[0]);
		long y = Integer.parseInt(input[1]);
		long z = (y*100)/x;
		int left = 0, right = x;
		while(left <= right) {
			int mid = (left+right)/2;
			long nz = ((y+mid)*100)/(x+mid);
			if(z == nz) {
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		if(z >= 99) {
			bw.write("-1");
		}
		else {
			bw.write(right+1+"");
		}
		bw.flush();
		
	}

}
