import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(bf.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        long[] cnt = new long[m];
        for (int i = 0; i < n; i++) {
            cnt[(int) (arr[i] % m)]++;
        }

        long answer = 0;
        answer += cnt[0];
        for (int i = 0; i < m; i++) {
            if (cnt[i] >= 2) {
                answer += (cnt[i] * (cnt[i] - 1)) / 2;
            }
        }

        System.out.println(answer);
    }
}
