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
    static int[][] parent;
    static int[] level;

    static int k = 0;

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

        // 2^k >= n을 만족하는 최소 k
        int tmp = 1;
        while (tmp <= n) {
            tmp <<= 1;
            k++;
        }

        // 각 노드들의 레벨 초기화, 부모 노드 초기화
        parent = new int[n + 1][k];
        level = new int[n + 1];
        Arrays.fill(level, -1);

        // 부모 테이블 업데이트
        setParent();

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

    private static void setParent() {
        dfs(1, 0);
        for (int i = 1; i < k; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static int lca(int a, int b) {
        // 0. a가 더 깊도록 설정
        if (level[a] < level[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 1. 두 노드의 깊이 통일
        for (int i = k - 1; i > -1; i--) {
            if (level[a] - level[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        // 부모가 같도록
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

    private static void dfs(int node, int depth) {
        level[node] = depth;

        for (int next : tree[node]) {
            if (level[next] != -1) {
                continue;
            }

            parent[next][0] = node;

            dfs(next, depth + 1);
        }
    }
}
