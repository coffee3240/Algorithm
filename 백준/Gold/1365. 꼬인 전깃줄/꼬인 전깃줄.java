import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;   // 전봇대 개수
    static int[] arr; // 연결정보

    static int[] lis;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis = new int[n];
        lis[0] = arr[0];

        int len = 1;
        for (int i = 1; i < n; i++) {
            if (lis[len - 1] < arr[i]) {
                lis[len] = arr[i];
                len++;
            } else {
                int idx = binarySearch(-1, len - 1, arr[i]);
                lis[idx] = arr[i];
            }
        }

        System.out.println(n - len);
    }

    private static int binarySearch(int left, int right, int target) {
        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (lis[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }
}
