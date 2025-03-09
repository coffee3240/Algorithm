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
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mix = arr[left] + arr[right];
            if (Math.abs(mix) < Math.abs(answer)) {
                answer = mix;
            }

            if (mix < 0) {
                left++;
            } else {
                right--;
            }
        }

        return answer;
    }
}