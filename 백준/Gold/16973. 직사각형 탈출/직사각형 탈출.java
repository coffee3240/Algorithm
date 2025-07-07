import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map;
    static int h, w;
    static int sy, sx;
    static int fy, fx;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < m + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        prefixSum = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + map[i][j];
            }
        }

        st = new StringTokenizer(bf.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());
        fx = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[n + 1][m + 1];

        queue.add(new Node(sy, sx, 0));
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == fy && cur.x == fx) {
                return cur.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (OOB(ny, nx) || visit[ny][nx] || cantMove(ny, nx)) {
                    continue;
                }

                queue.add(new Node(ny, nx, cur.cnt + 1));
                visit[ny][nx] = true;
            }
        }
        return -1;
    }

    private static boolean cantMove(int y, int x) {
        return prefixSum[y + h - 1][x + w - 1]
                - prefixSum[y + h - 1][x - 1]
                - prefixSum[y - 1][x + w - 1]
                + prefixSum[y - 1][x - 1]
                != 0;
    }

    private static boolean OOB(int y, int x) {
        return y < 1 || y + h - 1 > n || x < 1 || x + w - 1 > m;
    }

    private static class Node {
        int y;
        int x;
        int cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
