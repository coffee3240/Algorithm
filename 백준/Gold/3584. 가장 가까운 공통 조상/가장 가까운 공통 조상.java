import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(bf.readLine());

            parent = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b] = a;
            }

            // a, b의 공통 조상 찾기
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            visit = new boolean[n + 1];
            sb.append(solve(a, b)).append('\n');
        }

        System.out.println(sb);
    }

    private static int solve(int a, int b) {
        while (a > 0) { // 루트노드의 parent 노드는 0
            visit[a] = true;
            a = parent[a];
        }

        while (b > 0) {
            if (visit[b]) {
                return b;
            }

            b = parent[b];
        }

        return -1;
    }
}
