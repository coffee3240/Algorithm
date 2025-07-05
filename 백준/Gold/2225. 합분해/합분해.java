import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // dp[i][j] = i개의 0 이상 정수를 더해 합이 j가 되는 경우의 수
        int[][] dp = new int[k + 1][n + 1];

        // 합이 0인 경우는 모든 수가 0인 경우 1가지
        for (int i = 0; i < k + 1; i++) {
            dp[i][0] = 1;
        }

        // 1개를 사용하여 n를 만드는 경우는 n을 사용하는 1가지
        for (int i = 0; i < n + 1; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < k + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // i번째 선택하는 정수가 0 ~ j까지
                int sum = 0;
                for (int l = 0; l < j + 1; l++) {
                    sum = (sum + (dp[i - 1][j - l])) % MOD;
                }

                dp[i][j] = sum;
            }
        }

        System.out.println(dp[k][n]);
    }
}
