import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;    // 세로, 가로
    static int[][] arr;
    static boolean[][] visit;

    // 상 하 좌 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int minAnswer = Integer.MAX_VALUE;
    static int maxAnswer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n * 2 + 1][m * 2 + 1];    // (홀수, 홀수)가 원래 칸

        int T = Integer.parseInt(bf.readLine());    // 선분의 수
        while (T-- > 0) {
            st = new StringTokenizer(bf.readLine());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());

            if (sx == ex) { // 세로 선분
                int minY = Math.min(sy, ey);
                int maxY = Math.max(sy, ey);

                for (int i = minY * 2; i < maxY * 2 + 1; i++) {
                    arr[i][sx * 2] = 1;
                }
            } else if (sy == ey) {  // 가로 선분
                int minX = Math.min(sx, ex);
                int maxX = Math.max(sx, ex);

                for (int i = minX * 2; i < maxX * 2 + 1; i++) {
                    arr[sy * 2][i] = 1;
                }
            }
        }

        visit = new boolean[n * 2 + 1][m * 2 + 1];
        solve();

        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }

    private static void solve() {
        for (int i = 0; i < n * 2 + 1; i++) {
            for (int j = 0; j < m * 2 + 1; j++) {
                if (!visit[i][j] && arr[i][j] != 1) {
                    int cnt = bfs(i, j);
                    minAnswer = Math.min(minAnswer, cnt);
                    maxAnswer = Math.max(maxAnswer, cnt);
                }
            }
        }
    }

    private static int bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});
        visit[y][x] = true;

        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[0] % 2 == 1 && now[1] % 2 == 1) {
                cnt++;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (OOB(ny, nx) || visit[ny][nx] || arr[ny][nx] == 1) {
                    continue;
                }

                visit[ny][nx] = true;
                queue.add(new int[]{ny, nx});
            }
        }

        return cnt;
    }

    private static boolean OOB(int y, int x) {
        return y >= n * 2 + 1 || y < 0 || x >= m * 2 + 1 || x < 0;
    }
}
