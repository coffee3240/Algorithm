import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, t; // 서브태스크 개수, 대회 제한 시간
    static int[][] scoreArr;
    static int[][] timeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // 점수 입력
        scoreArr = new int[3][n + 1];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < n + 1; j++) {
                scoreArr[i][j] = scoreArr[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        // 시간 입력
        timeArr = new int[3][n + 1];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < n + 1; j++) {
                timeArr[i][j] = timeArr[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 1], scoreArr[2][i]);
        }

        int answer = 0;
        for (int i = 0; i < n + 1; i++) {
            int idx = n;
            for (int j = 0; j < n + 1; j++) {
                int time = timeArr[0][i] + timeArr[1][j];

                if (time > t) {
                    break;
                }

                // 시간은 항상 오름차순
                while (time + timeArr[2][idx] > t) {
                    idx--;
                }

                answer = Math.max(answer, scoreArr[0][i] + scoreArr[1][j] + dp[idx]);
            }
        }

        System.out.println(answer);
    }
}