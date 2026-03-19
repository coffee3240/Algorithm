import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][][] arr;

    static int answer;

    static int[] dy = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    static boolean[] select;
    static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < 5; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        select = new boolean[5];
        order = new int[5];
        answer = Integer.MAX_VALUE;
        turnMaze(0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer - 1);
    }

    private static void turnMaze(int cnt) {
        if(cnt == 5) {
            // 미로 설계
            assignMaze(0);
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 현재 미로를 돌린다.
            for (int j = 0; j < i; j++) {
                turn(cnt);
            }

            // 다음 미로
            turnMaze(cnt + 1);

            // 현재 미로 원상복구
            for (int j = 0; j < i; j++) {
                rollback(cnt);
            }
        }
    }

    private static void assignMaze(int cnt) {
        if(cnt == 5) {
            // 미로 설계 완료
            answer = Math.min(answer, go());
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!select[i]) {
                select[i] = true;
                order[cnt] = i;
                assignMaze(cnt + 1);
                select[i] = false;
            }
        }
    }

    private static int go() {
        int[][][] copyArr = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            System.arraycopy(arr[order[i]], 0, copyArr[i], 0, arr[order[i]].length);
        }

        if (copyArr[0][0][0] == 0 || copyArr[4][4][4] == 0) {
            return Integer.MAX_VALUE;
        }

        int[][][] visited = new int[5][5][5];
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, 0));
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                int y = p.y;
                int x = p.x;
                int z = p.z;

                if(y == 4 && x == 4 && z == 4) return visited[4][4][4];

                for (int j = 0; j < 6; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];
                    int nz = z + dz[j];

                    if(OOB(ny, nx, nz) || visited[ny][nx][nz] != 0 || copyArr[ny][nx][nz] == 0) continue;

                    queue.offer(new Point(ny, nx, nz));
                    visited[ny][nx][nz] = visited[y][x][z] + 1;
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean OOB(int y, int x, int z) {
        return y < 0 || y > 4 || x < 0 || x > 4 || z < 0 || z > 4;
    }

    private static void rollback(int cnt) {
        int[][] tmp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp[i][j] = arr[cnt][j][5 - 1 - i];
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[cnt][i][j] = tmp[i][j];
            }
        }
    }

    private static void turn(int cnt) {
        int[][] tmp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp[i][j] = arr[cnt][5 - 1 - j][i];
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[cnt][i][j] = tmp[i][j];
            }
        }
    }

    static class Point {
        int y;
        int x;
        int z;

        public Point(int y, int x, int z) {
            this.y = y;
            this.x = x;
            this.z = z;
        }
    }
}