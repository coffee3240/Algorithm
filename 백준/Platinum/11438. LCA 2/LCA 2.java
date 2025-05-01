import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Integer>[] graph;
    static int[][] parent;
    static int[] level;

    static int k;

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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        initK();

        initParent();

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(bf.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append('\n');
        }

        System.out.println(sb);
    }

    private static int lca(int a, int b) {
        // a가 더 밑에 있도록
        if (level[a] < level[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 레벨 맞추기
        for (int i = k - 1; i > -1; i--) {
            if (level[a] - level[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        // 부모 통일
        if (a == b) {
            return a;
        }

        for (int i = k - 1; i > - 1; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    private static void initParent() {
        level = new int[n + 1];
        Arrays.fill(level, -1);

        parent = new int[n + 1][k];

        dfs(1, 0);

        for (int i = 1; i < k; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static void dfs(int node, int depth) {
        level[node] = depth;

        for (int next : graph[node]) {
            if (level[next] != -1) {
                continue;
            }

            parent[next][0] = node;

            dfs(next, depth + 1);
        }
    }

    private static void initK() {
        k = 0;
        int tmp = 1;
        while (tmp <= n) {
            tmp <<= 1;
            k++;
        }
    }
}
