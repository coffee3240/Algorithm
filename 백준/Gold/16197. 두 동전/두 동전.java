import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int n, m;    // 세로 크기, 가로 크기
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        int y1 = -1;
        int x1 = -1;
        int y2 = -1;
        int x2 = -1;
        for (int i = 0; i < n; i++) {
            String row = bf.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'o') {
                    if (y1 == -1) {
                        y1 = i;
                        x1 = j;
                    }
                    else {
                        y2 = i;
                        x2 = j;
                    }
                }
            }
        }

        System.out.println(bfs(y1, x1, y2, x2));
    }

    private static int bfs(int y1, int x1, int y2, int x2) {
        Set<String> visit = new HashSet<>();
        visit.add(getStr(y1, x1, y2, x2));

        Queue<Coins> queue = new ArrayDeque<>();
        queue.add(new Coins(y1, x1, y2, x2));

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Coins coins = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int ny1 = coins.y1 + dy[i];
                    int nx1 = coins.x1 + dx[i];
                    int ny2 = coins.y2 + dy[i];
                    int nx2 = coins.x2 + dx[i];

                    // 둘이 동시에 떨어지는 경우
                    if (OOB(ny1, nx1) && OOB(ny2, nx2)) {
                        continue;
                    }

                    // 하나만 떨어지는 경우
                    if (OOB(ny1, nx1) || OOB(ny2, nx2)) {
                        return step;
                    }

                    // 벽을 만난 경우
                    if (map[ny1][nx1] == '#') {
                        ny1 = coins.y1;
                        nx1 = coins.x1;
                    }

                    // 벽을 만난 경우
                    if (map[ny2][nx2] == '#') {
                        ny2 = coins.y2;
                        nx2 = coins.x2;
                    }

                    // 이미 방문한 경우
                    if (visit.contains(getStr(ny1, nx1, ny2, nx2))) {
                        continue;
                    }

                    // 둘 다 안떨어진 경우
                    visit.add(getStr(ny1, nx1, ny2, nx2));
                    queue.add(new Coins(ny1, nx1, ny2, nx2));
                }
            }

            step++;
            // 버튼을 10번보다 많이 눌러야 한다면 -1을 출력한다.
            if (step > 10) {
                return -1;
            }
        }

        return -1;
    }

    private static String getStr(int y1, int x1, int y2, int x2) {
        return "" + y1 + "_" + x1 + "_" + y2 + "_" + x2;
    }

    private static boolean OOB(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    private static class Coins {
        int y1, x1;
        int y2, x2;

        public Coins(int y1, int x1, int y2, int x2) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
        }
    }
}
