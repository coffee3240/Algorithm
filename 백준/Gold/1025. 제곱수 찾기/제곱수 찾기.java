import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] arr;

    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = bf.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int di = -n; di < n; di++) {
                    for (int dj = -m; dj < m; dj++) {
                        if (di == 0 && dj == 0) {
                            continue;
                        }
                        solve(i, j, di, dj);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void solve(int sx, int sy, int dx, int dy) {
        int x = sx;
        int y = sy;

        int num = 0;
        while (x > -1 && x < n && y > -1 && y < m) {
            num = num * 10 + (arr[x][y] - '0');
            if (isPerfectSquare(num)) {
                answer = Math.max(answer, num);
            }

            x += dx;
            y += dy;
        }
    }

    private static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
