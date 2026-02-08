import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int startY = 0, startX = 0;
        int[][] cPos = new int[2][2];
        int cIdx = 0;

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = bf.readLine();
            for (int j = 0; j < row.length(); j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'S') {
                    startY = i;
                    startX = j;
                } else if (map[i][j] == 'C') {
                    cPos[cIdx][0] = i;
                    cPos[cIdx][1] = j;
                    cIdx++;
                }
            }
        }

        // c는 최대 2개
        // dir 4 -> 시작 상태
        boolean[][][][] visited = new boolean[n][m][5][4];

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(startY, startX, 4, 0, 0));
        visited[startY][startX][4][0] = true;

        int answer = -1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // c 2개 방문
            if (cur.mask == 3) {
                answer = cur.dist;
                break;
            }

            for (int i = 0; i < 4; i++) {
                // 같은 방향 금지
                if (cur.dir == i) {
                    continue;
                }

                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                // 맵 범위 초과 || 이동 불가 지역
                if (OOB(ny, nx) || map[ny][nx] == '#') {
                    continue;
                }

                int nextMask = cur.mask;

                if (map[ny][nx] == 'C') {
                    if (ny == cPos[0][0] && nx == cPos[0][1]) {
                        nextMask |= 1;
                    } else if (ny == cPos[1][0] && nx == cPos[1][1]) {
                        nextMask |= 2;
                    }
                }

                if (visited[ny][nx][i][nextMask]) {
                    continue;
                }

                visited[ny][nx][i][nextMask] = true;
                queue.add(new Node(ny, nx, i, nextMask, cur.dist + 1));
            }
        }

        System.out.println(answer);
    }

    private static boolean OOB(int y, int x) {
        return y >= n || y < 0 || x >= m || x < 0;
    }

    private static class Node {
        int y;
        int x;
        int dir;
        int mask;
        int dist;

        public Node(int y, int x, int dir, int mask, int dist) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.mask = mask;
            this.dist = dist;
        }
    }
}
