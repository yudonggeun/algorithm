package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1449 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		int tapeSize = Integer.parseInt(input[1]);
		int n = Integer.parseInt(input[0]);
		input = br.readLine().split(" ");
		int [] hole = new int [n];
		for(int i = 0; i< n ; i++) {
			hole[i] = Integer.parseInt(input[i]);
		}
		//
		Arrays.sort(hole);
		int tape = 0;
		int count = 0;
		for(int i = 0; i< n; i++) {
			if(tape == 0) {
				tape = tapeSize-1;
				count++;
			}
			else if(tape < hole[i]-hole[i-1]) {
				tape = tapeSize-1;
				count++;
			}
			else {
				tape -= hole[i]-hole[i-1];
			}
		}
		
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
