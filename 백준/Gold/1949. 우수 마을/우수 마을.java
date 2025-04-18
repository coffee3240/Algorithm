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

        recur(1, -1);
        System.out.println(Math.max(dp[0][1], dp[1][1]));
    }

    private static void recur(int now, int prev) {
        dp[0][now] = 0;
        dp[1][now] = count[now];

        for (int next : graph[now]) {
            if (next == prev) {
                continue;
            }

            recur(next, now);

            dp[0][now] += Math.max(dp[0][next], dp[1][next]);
            dp[1][now] += dp[0][next];
        }
    }
}
