import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(bf.readLine());    // 학생의 수

        // 학생 수가 3 미만이면 팀을 만들 수 없다.
        if (n < 3) {
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(bf.readLine());
        int[] student = new int[n];
        for (int i = 0; i < n; i++) {
            student[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(student);

        // 두 학생의 성적을 더하고(A) 배열에서 -A의 수를 구한다.
        long answer = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = student[i] + student[j];

                // 다른 한 명은 인덱스 j보다 오른쪽에 있어야함
                int lower = lower(j, n, student, -sum);
                int upper = upper(j, n, student, -sum);

                answer += (upper - lower);
            }
        }

        System.out.println(answer);
    }

    private static int lower(int left, int right, int[] student, int target) {
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (student[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    private static int upper(int left, int right, int[] student, int target) {
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (student[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
