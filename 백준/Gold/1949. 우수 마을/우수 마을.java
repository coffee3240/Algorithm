import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] count;
    static List<Integer>[] graph;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        count = new int[n + 1];
        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n + 1; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dp = new int[2][n + 1];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(Math.max(recur(1, -1, 0), recur(1, -1, 1) + count[1]));
    }

    private static int recur(int now, int prev, int onOff) {
        if (dp[onOff][now] != -1) {
            return dp[onOff][now];
        }

        dp[onOff][now] = 0;
        for (int next : graph[now]) {
            if (next == prev) {
                continue;
            }

            if (onOff == 0) {   // 우수마을 X, 현재 우수마을이 아닌 경우, 다음 마을은 우수마을일수도 아닐수도 있음.
                dp[onOff][now] += Math.max(recur(next, now, 1) + count[next], recur(next, now, 0));
            } else {    // 우수마을 O
                dp[onOff][now] += recur(next, now, 0);  // 현재 우수마을인 경우, 다음 마을은 우수마을이 아님.
            }
        }

        return dp[onOff][now];
    }
}
