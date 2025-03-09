import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, k;    // 커피의 개수, 섭취해야하는 카페인 양
    static int[] caffeine;
    static int[][] dp;

    static final int INF = 1000;    // Integer.MAX_VALUE로 할 경우 recur함수 중 select의 값이 음수가 될 수 있으므로 따로 설정함

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        caffeine = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            caffeine[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = recur(n - 1, k);
        System.out.println(answer == INF ? -1 : answer);
    }

    private static int recur(int index, int targetCaffeine) {
        if (targetCaffeine == 0) {
            return 0;
        }

        if (index < 0 || targetCaffeine < 0) {
            return INF;
        }

        if (dp[index][targetCaffeine] != -1) {
            return dp[index][targetCaffeine];
        }

        int notSelect = recur(index - 1, targetCaffeine);
        int select = INF;
        if (targetCaffeine >= caffeine[index]) {    // 현재 커피를 선택할 수 있는 경우
            select = recur(index - 1, targetCaffeine - caffeine[index]) + 1;
        }

        dp[index][targetCaffeine] = Math.min(notSelect, select);

        return dp[index][targetCaffeine];
    }
}
