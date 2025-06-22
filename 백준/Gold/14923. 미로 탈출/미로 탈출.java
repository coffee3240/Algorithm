import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int hx, hy;
    static int ex, ey;

    static int[][] map;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean[][][] visit;
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        hx = Integer.parseInt(st.nextToken()) - 1;
        hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(bf.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][m][2];
        dist = new int[n][m][2];

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();

        visit[hx][hy][0] = true;
        dist[hx][hy][0] = 0;
        queue.add(new Node(hx, hy, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.x == ex && cur.y == ey) {
                return dist[cur.x][cur.y][cur.broken];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (OOB(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] == 0 && !visit[nx][ny][cur.broken]) {
                    visit[nx][ny][cur.broken] = true;
                    dist[nx][ny][cur.broken] = dist[cur.x][cur.y][cur.broken] + 1;
                    queue.add(new Node(nx, ny, cur.broken));
                } else if (map[nx][ny] == 1 && cur.broken == 0 && !visit[nx][ny][1]) {
                    visit[nx][ny][1] = true;
                    dist[nx][ny][1] = dist[cur.x][cur.y][cur.broken] + 1;
                    queue.add(new Node(nx, ny, 1));
                }
            }
        }

        return -1;
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private static class Node {
        int x;
        int y;
        int broken; // 1 is broken

        public Node(int x, int y, int broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }
}
