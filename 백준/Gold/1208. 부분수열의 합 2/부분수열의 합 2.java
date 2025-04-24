import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class Main {

    static int[] arr;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());   // 정수의 개수
        int s = Integer.parseInt(st.nextToken());   // 타겟

        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> countMap = new HashMap<>();

        comb(0, n / 2, 0, sum -> countMap.put(sum, countMap.getOrDefault(sum, 0) + 1));
        comb(n / 2, n, 0, sum -> answer += countMap.getOrDefault(s - sum, 0));

        System.out.println(s == 0 ? answer - 1 : answer);
    }

    private static void comb(int startIdx, int endIdx, int sum, Consumer<Integer> callback) {
        if (startIdx == endIdx) {
            callback.accept(sum);
            return;
        }

        comb(startIdx + 1, endIdx, sum, callback);
        comb(startIdx + 1, endIdx, sum + arr[startIdx], callback);
    }
}
