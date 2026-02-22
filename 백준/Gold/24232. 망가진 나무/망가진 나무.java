import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Edge>[] graph;
    static int[] dp;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 일단 u가 루트로 생각하고 저장
            graph[u].add(new Edge(v, 0, i));
            graph[v].add(new Edge(u, 1, i));
        }

        dp = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(1);

        visited = new boolean[n + 1];
        dfs2(1);

        int min = dp[1];
        int root = 1;
        for (int i = 2; i < n + 1; i++) {
            if (dp[i] < min) {
                min = dp[i];
                root = i;
            }
        }

        answer = new int[n - 1];
        visited = new boolean[n + 1];
        dfs3(root);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append(answer[i]);
        }
        System.out.println(sb);
    }

    private static void dfs3(int node) {
        visited[node] = true;

        for (Edge edge : graph[node]) {
            if (!visited[edge.to]) {
                if (edge.cost == 1) {
                    answer[edge.idx] = 1;
                }
                dfs3(edge.to);
            }
        }
    }

    private static void dfs2(int node) {
        visited[node] = true;

        for (Edge edge : graph[node]) {
            if (visited[edge.to]) {
                continue;
            }

            dp[edge.to] = edge.cost == 0 ? dp[node] + 1 : dp[node] - 1;

            dfs2(edge.to);
        }
    }


    private static void dfs(int node) {
        visited[node] = true;

        for (Edge edge : graph[node]) {
            if (visited[edge.to]) {
                continue;
            }

            dp[1] += edge.cost;
            dfs(edge.to);
        }
    }

    private static class Edge {
        int to;
        int cost;
        int idx;

        public Edge(int to, int cost, int idx) {
            this.to = to;
            this.cost = cost;
            this.idx = idx;
        }
    }
}
