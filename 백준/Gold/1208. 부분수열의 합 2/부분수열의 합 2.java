import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1 <= n <= 40
 *
 * 조합 => 2^40
 * mitm => 2^20 * 2
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());   // 정수의 개수
        int s = Integer.parseInt(st.nextToken());   // 타겟

        int[] arr1 = new int[n / 2];
        int[] arr2 = new int[n - (n / 2)];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n / 2; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - (n / 2); i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        // arr1 원소들의 조합으로 만들 수 있는 합
        int[] sum1 = comb(arr1);

        // arr2 원소들의 조합으로 만들 수 있는 합
        int[] sum2 = comb(arr2);

        // 이분탐색을 위한 정렬
        Arrays.sort(sum2);

        long answer = 0;
        for (int sum : sum1) {
            answer += upperBound(s - sum, sum2) - lowerBound(s - sum, sum2);
        }

        System.out.println(s == 0 ? answer - 1 : answer);
    }

    private static int upperBound(int target, int[] arr) {
        int left = -1;
        int right = arr.length;

        while (left + 1 < right) {
            int mid = ((left + right) >> 1);
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    private static int lowerBound(int target, int[] arr) {
        int left = -1;
        int right = arr.length;

        while (left + 1 < right) {
            int mid = ((left + right) >> 1);
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    private static int[] comb(int[] arr) {
        // 공집합 미포함
        int[] sum = new int[1 << arr.length];

        for (int i = 1; i < (1 << arr.length); i++) {
            int s = 0;
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) > 0) {
                    s += arr[j];
                }
            }

            sum[i] = s;
        }

        return sum;
    }
}
