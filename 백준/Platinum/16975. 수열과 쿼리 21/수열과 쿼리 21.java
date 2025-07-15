import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;
    static long[] tree;
    static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());
        arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new long[4 * n];
        lazy = new long[4 * n];
        init(1, n, 1);

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(bf.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(bf.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(1, n, 1, left, right, value);
            } else if (command == 2) {
                int target = Integer.parseInt(st.nextToken());
                sb.append(query(1, n, 1, target, target)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long query(int start, int end, int node, int left, int right) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int left, int right, int diff) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (right < start || end < left) {
            return;
        }

        if (left <= start && end <= right) {
            tree[node] += (end - start + 1) * diff;
            if (start != end) {
                lazy[node * 2] += diff;
                lazy[node * 2 + 1] += diff;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, diff);
        update(mid + 1, end, node * 2 + 1, left, right, diff);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }
}
