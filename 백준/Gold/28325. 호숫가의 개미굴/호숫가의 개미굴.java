import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }

    private static int solve() {
        boolean[] visit = new boolean[n];
        int startIndex = -1;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                answer += arr[i];
                visit[i] = true;
                startIndex = i;
            }
        }

        if (answer == 0) {
            return n / 2;
        }

        for (int i = startIndex; i < startIndex + n; i++) {
            int index = i % n;

            if (arr[index] != 0 || visit[index]) {
                continue;
            }

            int cnt = 0;
            int j = index;
            while (arr[j] == 0 && !visit[j]) {
                cnt++;
                visit[j] = true;
                j++;
                i = j;
                j = j % n;
            }

            answer += ((cnt + 1) / 2);
        }

        return answer;
    }
}
