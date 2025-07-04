import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        map = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < n + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0: 가로, 1: 세로, 2: 대각
        dp = new int[n + 1][n + 1][3];

        // 첫 위치가 벽인지 빈 칸인지
        dp[1][2][0] = (map[1][2] == 0) ? 1 : 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (map[i][j] == 1) {
                    continue;
                }

                // 놓는 형태 (이전 가능 형태)

                // 가로 (가로, 대각)
                if (j - 1 >= 1 && map[i][j] == 0) {
                    dp[i][j][0] += (dp[i][j - 1][0] + dp[i][j - 1][2]);
                }

                // 세로 (세로, 대각)
                if (i - 1 >= 1 && map[i][j] == 0) {
                    dp[i][j][1] += (dp[i - 1][j][1] + dp[i - 1][j][2]);
                }

                // 대각 (가로, 세로, 대각)
                if (i - 1 >= 1 && j - 1 >= 1
                        && map[i - 1][j] == 0 && map[i][j - 1] == 0 && map[i][j] == 0
                ) {
                    dp[i][j][2] += (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]);
                }
            }
        }

        int answer = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
        System.out.println(answer);
    }
}
