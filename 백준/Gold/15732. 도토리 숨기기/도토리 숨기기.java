import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;   // 상자의 개수
    static int k;   // 규칙의 개수
    static long d;   // 도토리의 개수

    static int[] A; // A[i]: i번째 규칙의 시작 상자
    static int[] B; // B[i]: i번째 규칙의 끝 상자
    static int[] C; // C[i]: i번째 규칙의 간격

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Long.parseLong(st.nextToken());

        A = new int[k];
        B = new int[k];
        C = new int[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bs());
    }

    private static int bs() {
        int left = 0;
        int right = n;

        while (left + 1 < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static boolean check(int box) {
        long count = 0;
        for (int i = 0; i < k; i++) {
            if (box < A[i]) {   // 셀 수 없는 경우
                continue;
            }

            count += (Math.min(box, B[i]) - A[i]) / C[i] + 1;
        }

        return count < d;
    }
}
