package baekjoon;
import java.io.*;

public class baekjoon1543 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String document = br.readLine();
		String word = br.readLine();
		int count = 0;
		
		for(int i = 0; i< document.length() - word.length() +1; i++) {
			if(document.charAt(i) == word.charAt(0)) {
				String tem = document.substring(i, i+word.length());
				if(tem.equals(word)) {
					count++;
					i += word.length()-1;
				}								
			}
		}
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();
	}
}