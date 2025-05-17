import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        tree = new long[4 * n];
        init(1, n, 1);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, n, 1, b, diff);
            } else if (a == 2) {
                sb.append(sum(1, n, 1, b, (int) c)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖
        if (right < start || end < left) {
            return 0;
        }

        // 완전 범위 안
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 부분 범위 안
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int index, long diff) {
        // 범위 밖
        if (index < start || index > end) {
            return;
        }

        // 업데이트
        tree[node] += diff;

        // 리프노드 도달
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    private static long init(int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        return tree[node];
    }
}
