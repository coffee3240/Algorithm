import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 인덱스를 맞추기 위해 1부터 시작
        arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        tree = new int[4 * n];
        init(1, n, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(query(1, n, left, right, 1)).append('\n');
        }
        System.out.println(sb);
    }

    private static int query(int start, int end, int left, int right, int node) {
        // 구간을 벗어난 경우
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }

        // 구간 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        int mid = (start + end) / 2;
        return Math.min(query(start, mid, left, right, node * 2), query(mid + 1, end, left, right, node * 2 + 1));
    }


    private static int init(int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
        return tree[node];
    }
}