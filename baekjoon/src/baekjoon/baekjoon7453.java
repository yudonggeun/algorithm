package baekjoon;
import java.util.*;
import java.io.*;
//합이 0인 네 정수
public class baekjoon7453 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n], b = new int[n], c = new int[n], d = new int[n];
		for(int i = 0; i< n;i++) {
			String [] input = br.readLine().split(" ");
			a[i] = Integer.parseInt(input[0]);
			b[i] = Integer.parseInt(input[1]);
			c[i] = Integer.parseInt(input[2]);
			d[i] = Integer.parseInt(input[3]);
		}
		int[] ab = new int[n*n];
		int[] cd = new int[n*n];
		for(int i = 0; i< n; i++) {
			for(int j = 0; j < n; j++) {
				ab[i*n+j] = c[i]+d[j];
				cd[i*n+j] = a[i]+b[j];
			}
		}
		Arrays.sort(ab);
		Arrays.sort(cd);
		long answer = 0;
		for(int i = 0; i< n*n; i++) {
			int target = -ab[i];
			long gap = upperCase(cd, target)-lowerCase(cd, target);
			long count = 1;
			while(i+1 != n*n && ab[i] == ab[i+1]) {
				count++;
				i++;
			}
			answer += gap*count;
		}
		bw.write(answer+""); 
		bw.flush();
		
	}
	public static int upperCase(int[] list, int target) {
		int left = 0;
        int right = list.length-1;
        int mid;
        while (right >= left) {
            mid = (left + right) / 2;
            if (list[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
	}
	public static int lowerCase(int[] list, int target) {
		int left = 0;
        int right = list.length-1;
        int mid;
        while (right >= left) {
            mid = (left + right) / 2;
            if (list[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
	}
}