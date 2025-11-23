import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // bs
        int left = 1;
        int right = n + 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (!check(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(left);
    }

    private static boolean check(int target) {
        int count = 0;
        int left = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < n; right++) {
            int card = arr[right];
            map.put(card, map.getOrDefault(card, 0) + 1);

            while (map.get(card) > 1) {
                int leftValue = arr[left];
                map.put(leftValue, map.get(leftValue) - 1);
                left++;
            }

            int length = right - left + 1;

            if (length >= target) {
                count++;
                if (count >= m) {
                    return true;
                }

                map.clear();
                left = right + 1;
            }
        }

        return false;
    }
}
