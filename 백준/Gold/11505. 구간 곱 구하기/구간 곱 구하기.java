import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;

    static int[] arr;
    static long[] tree;

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        tree = new long[4 * n];
        Arrays.fill(tree, 1);

        init(1, n, 1);

        StringBuilder sb = new StringBuilder();
        int total = m + k;
        while (total-- > 0) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(1, n, 1, index, value);
            } else if (cmd == 2) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(1, n, 1, left, right)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long query(int start, int end, int node, int left, int right) {
        if (right < start || end < left) {
            return 1;
        }

        if (left <= start && end <= right) {
            return tree[node] % MOD;
        }

        int mid = (start + end) / 2;
        return (query(start, mid, node * 2, left, right) * query(mid + 1, end, node * 2 + 1, left, right)) % MOD;
    }

    private static void update(int start, int end, int node, int index, int value) {
        if (index < start || end < index) {
            return;
        }

        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, value);
        update(mid + 1, end, node * 2 + 1, index, value);

        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }

    private static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }
}
