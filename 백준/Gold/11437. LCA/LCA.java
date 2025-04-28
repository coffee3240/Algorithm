import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Integer>[] tree;
    static int[] parent;
    static int[] level;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        tree = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }


        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        // 각 노드들의 레벨 초기화, 부모 노드 초기화
        parent = new int[n + 1];
        level = new int[n + 1];
        Arrays.fill(level, -1);
        dfs(1, 0);

        StringBuilder sb = new StringBuilder();

        // 확인하고 싶은 노드 쌍의 수
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
        // 1. 두 노드의 깊이 통일
        while (level[a] != level[b]) {
            if (level[a] > level[b]) {
                a = parent[a];
            } else {
                b = parent[b];
            }
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void dfs(int node, int depth) {
        level[node] = depth;

        for (int next : tree[node]) {
            if (level[next] != -1) {
                continue;
            }

            parent[next] = node;

            dfs(next, depth + 1);
        }
    }
}
