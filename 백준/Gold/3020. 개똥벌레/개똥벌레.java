import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[] bottom = new int[h + 1];
		int[] top = new int[h + 1];
		
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(bf.readLine());
			if (i % 2 == 0) {
				bottom[0]++;
				bottom[input]--;
			} else {
				top[h - input + 1]++;
			}
		}
		
		for (int i = 1; i < h + 1; i++) {
			bottom[i] += bottom[i - 1];
			top[i] += top[i - 1];
		}
		
		int[] sum = new int[h];
		for (int i = 0; i < h; i++) {
			sum[i] = bottom[i] + top[i + 1];
		}
		
		Arrays.sort(sum);
		
		int cnt = 1;
		for (int i = 1; i < h; i++) {
			if (sum[i] != sum[0]) {
				break;
			}
			cnt++;
		}
		
		System.out.println(sum[0] + " " + cnt);
	}

}