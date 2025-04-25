import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 틀린 이유
 * 1. 범위 체크할 때 (y + size), (x + size)에 대해 검사해야하는데 (y + size)를 2번 체크함...
 */
public class Main {

    static int n = 10;
    static int[][] arr = new int[n][n];
    static int[] count = {0, 5, 5, 5, 5, 5};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0);

        // 모든 칸을 탐색하지 못한 경우엔 -1을 출력
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void recur(int num, int cnt) {
        // 모든 칸에 대해 탐색을 완료한 경우
        if (num == 100) {
            answer = Math.min(answer, cnt);
            return;
        }

        // 이미 더 적게 만드는 경우를 알고 있는 경우
        if (answer <= cnt) {
            return;
        }

        int y = num / n;
        int x = num % n;

        if (arr[y][x] == 1) {   // 색종이를 붙여야 하는 칸은 붙일 수 있는지 판단 후 다음 프로세스 진행
            for (int size = 5; size >= 1; size--) {
                if (count[size] > 0 && canPatch(size, y, x)) {
                    patch(size, y, x, 0);
                    count[size]--;
                    recur(num + 1, cnt + 1);
                    count[size]++;
                    patch(size, y, x, 1);
                }
            }
        } else {    // 색종이를 붙이지 않는 칸은 바로 다음 칸 탐색
            recur(num + 1, cnt);
        }
    }

    private static void patch(int size, int y, int x, int value) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                arr[i][j] = value;
            }
        }
    }

    private static boolean canPatch(int size, int y, int x) {
        if (y + size > n || x + size > n) {
            return false;
        }

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (arr[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
