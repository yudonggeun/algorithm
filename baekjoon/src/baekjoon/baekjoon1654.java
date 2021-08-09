package baekjoon;
import java.io.*;
//랜선자르기
public class baekjoon1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int k = Integer.parseInt(input[0]);
		int n = Integer.parseInt(input[1]);
		long [] wires = new long[k];
		long pre = 1, back = 0, target=0;
		for(int i = 0; i< k; i++) {
			wires[i] = Long.parseLong(br.readLine());
			back = Math.max(wires[i], back);
		}
		while(pre <= back) {
			long quotient  = 0;
			long nextQuotient  = 0;
			target = (pre+back)/2;
			for(long wire : wires) {
				quotient+= wire/target;
				nextQuotient +=wire/(target+1);
			}
			if(quotient  == n) {
				if(nextQuotient != n-1) {
					pre = target+1;
				}
				else {
					back = target;
					break;
				}
			}
			else if(quotient  < n) {
				back = target-1;
			}
			else {
				pre = target+1;
			}
		}
		bw.write(back+"");
		bw.flush();
		
		
	}

}
