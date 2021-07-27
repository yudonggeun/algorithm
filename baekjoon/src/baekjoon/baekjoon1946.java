package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1946 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 0; test < testCase; test++) {
			int count = Integer.parseInt(br.readLine());
			int[] data = new int[count];
			for(int i = 0; i< count ; i++) {
				String [] input = br.readLine().split(" ");
				int index = Integer.parseInt(input[0])-1;//성적 A의 등수를 인덱스로 활용
				data[index] = Integer.parseInt(input[1]);	
			}
			int minGradeB = count;
			int pass = 0;
			for(int B : data) {
				if(minGradeB >= B){
					minGradeB = B;
					pass++;
				}
			}
			bw.write(pass+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
