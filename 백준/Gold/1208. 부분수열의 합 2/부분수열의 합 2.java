import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static final int OFFSET = 2_000_000;

    static int n, s;
    static int[] arr;
    static int[] counts = new int[OFFSET * 2 + 1];
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());   // 정수의 개수
        s = Integer.parseInt(st.nextToken());   // 타겟

        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        combHalf(0,0);
        combHalfAndMatch(n / 2,0);

        System.out.println(s == 0 ? answer - 1 : answer);
    }

    private static void combHalfAndMatch(int idx, int sum) {
        if (idx == n) {
            int other = s - sum + OFFSET;
            if (0 <= other && other <= OFFSET * 2) {
                answer += counts[other];
            }
            return;
        }
        combHalfAndMatch(idx + 1, sum);
        combHalfAndMatch(idx + 1, arr[idx] + sum);
    }

    private static void combHalf(int idx, int sum) {
        if (idx == n / 2) {
            counts[sum + OFFSET]++;
            return;
        }

        combHalf(idx + 1, sum);
        combHalf(idx + 1, sum + arr[idx]);
    }
}
