import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시간 제한 1초
 *
 * 1. 백트래킹
 * - 로봇 청소기가 같은 칸을 여러 번 방문할 수 있으므로 제외
 *
 * 2. BFS + MST
 * - 시작점, 더러운 칸들 간 거리를 계산
 * - 이후, MST?
 * => 예제2번이 정상동작 X (정답: 0 -> 2 -> 3 -> 1)
 * => MST로 할 경우 (0, 2), (1, 3), (0, 1)을 순차적으로 연결하여 정답을 도출해내지 못함
 *
 * 3. BFS + 순열
 * -> 최대 10개라서 시간안에 동작
 */
public class Main {

    static int w, h; // 가로, 세로
    static char[][] arr;    // 맵
    static Map<Integer, Point> pointMap;    // <번호, 좌표>

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int number;
    static boolean[] select;
    static int[][] dist;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(bf.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                System.out.println(sb);
                return;
            }

            int startIndex = 0;
            number = 0;
            arr = new char[h][w];
            pointMap = new HashMap<>();
            for (int i = 0; i < h; i++) {
                String row = bf.readLine();
                for (int j = 0; j < w; j++) {
                    arr[i][j] = row.charAt(j);
                    if (arr[i][j] == '*' || arr[i][j] == 'o') {
                        if (arr[i][j] == 'o') {
                            startIndex = number;
                        }
                        pointMap.put(number, new Point(i, j));
                        arr[i][j] = Character.forDigit(number++, 10);
                    }
                }
            }

            // 노드 간 거리 계산
            dist = new int[number][number];
            for (int i = 0; i < number - 1; i++) {
                for (int j = i + 1; j < number; j++) {
                    int d = bfs(i, j);
                    dist[i][j] = d;
                    dist[j][i] = d;
                }
            }

            answer = Integer.MAX_VALUE;
            select = new boolean[number];
            select[startIndex] = true;
            perm(startIndex, 0, 0);

            sb.append(answer == Integer.MAX_VALUE ? -1 : answer).append('\n');
        }
    }

    private static void perm(int start, int index, int sum) {
        if (index == number - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < number; i++) {
            if (select[i] || start == i || dist[start][i] == 0 || dist[start][i] == Integer.MAX_VALUE) {
                continue;
            }

            select[i] = true;
            perm(i, index + 1, sum + dist[start][i]);
            select[i] = false;
        }
    }

    private static int bfs(int a, int b) {
        boolean[][] visit = new boolean[h][w];

        Point pointA = pointMap.get(a);
        Point pointB = pointMap.get(b);

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(pointA.y, pointA.x, 0));
        visit[pointA.y][pointA.x] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.y == pointB.y && now.x == pointB.x) {
                return now.count;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (OOB(ny, nx) || visit[ny][nx] || arr[ny][nx] == 'x') {
                    continue;
                }

                visit[ny][nx] = true;
                queue.add(new Point(ny, nx, now.count + 1));
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean OOB(int y, int x) {
        return y >= h || y < 0 || x >= w || x < 0;
    }

    private static class Point {
        int y;
        int x;
        int count;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
}
