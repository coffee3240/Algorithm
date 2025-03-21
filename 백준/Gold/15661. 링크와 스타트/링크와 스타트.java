import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n; // 사람 수
    static int[][] s;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        s = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < (1 << n) - 1; i++) { // 팀원은 1명 이상
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    start.add(j);
                } else {
                    link.add(j);
                }
            }

            answer = Math.min(answer, Math.abs(calc(start) - calc(link)));
        }

        System.out.println(answer);
    }

    private static int calc(List<Integer> team) {
        int power = 0;
        for (int i : team) {
            for (int j : team) {
                power += s[i][j];
            }
        }

        return power;
    }
}
