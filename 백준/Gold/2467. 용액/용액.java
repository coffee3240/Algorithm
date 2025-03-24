import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;   // 용액의 수
    static int[] arr; // 용액

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;

        int answer = Integer.MAX_VALUE;
        int ansLeft = -1;
        int ansRight = -1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (Math.abs(sum) < answer) {
                answer = Math.abs(sum);
                ansLeft = left;
                ansRight = right;
            }

            if (sum == 0) {
                break;
            } else if (sum < 0) { // right--를 할 경우 현재 sum보다 더 작아지므로
                left++;
            } else {
                right--;
            }
        }

        System.out.println(arr[ansLeft] + " " + arr[ansRight]);
    }
}
