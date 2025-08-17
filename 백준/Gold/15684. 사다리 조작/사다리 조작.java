import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, h;
    static boolean[][] isLadder;

    static int answer = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        isLadder = new boolean[h][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            isLadder[a][b] = true;
        }

        if (isSameStartAndEnd()) {
            System.out.println(0);
            return;
        }

        recur(0, 0);
        System.out.println(answer == 4 ? -1 : answer);
    }

    private static void recur(int depth, int start) {
        if (depth > 3 || depth >= answer) {
            return;
        }

        if (isSameStartAndEnd()) {
            answer = depth;
            return;
        }

        for (int i = start; i < (n - 1) * h; i++) {
            int y = i / (n - 1);
            int x = i % (n - 1);

            if (!isLadder[y][x]) {
                isLadder[y][x] = true;
                recur(depth + 1, i + 1);
                isLadder[y][x] = false;
            }
        }
    }

    private static boolean isSameStartAndEnd() {
        for (int i = 0; i < n; i++) {
            if (check(i) != i) {
                return false;
            }
        }
        return true;
    }

    private static int check(int x) {
        int col = x;
        for (int y = 0; y < h; y++) {
            if (col < n && isLadder[y][col]) {
                col++;
            } else if (col > 0 && isLadder[y][col - 1]) {
                col--;
            }
        }

        return col;
    }
}
