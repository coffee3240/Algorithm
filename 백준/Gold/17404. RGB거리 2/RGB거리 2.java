import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX = 1_000 * 1_000 + 1;

    static int n;
    static int[][] house;
    static int[][] dp;  // dp[i][j] : i번 집까지 칠했을 때 칠한 최소 비용

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        house = new int[3][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            house[0][i] = r;
            house[1][i] = g;
            house[2][i] = b;
        }

        int answer = MAX;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, solve(i));
        }

        System.out.println(answer);
    }

    private static int solve(int color) {
        dp = new int[3][n];

        // 초기화
        for (int i = 0; i < 3; i++) {
            dp[i][0] = (i == color) ? house[i][0] : MAX;
        }

        // 중간 집
        for (int i = 1; i < n; i++) {
            for (int c = 0; c < 3; c++) {
                // 현재 c 색상, 이전 집은 c 색상이 아니어야함.
                dp[c][i] = house[c][i] + Math.min(
                        dp[(c + 1) % 3][i - 1],
                        dp[(c + 2) % 3][i - 1]
                );
            }
        }

        // 마지막 집
        int answer = MAX;
        for (int c = 0; c < 3; c++) {
            // 시작 집과 같은 색
            if (c == color) {
                continue;
            }

            answer = Math.min(answer, dp[c][n - 1]);
        }

        return answer;
    }
}
