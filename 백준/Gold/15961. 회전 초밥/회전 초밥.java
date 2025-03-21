import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int n, d, k, c; // 초밥 접시 수, 초밥 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] dish = new int[n];
        for (int i = 0; i < n; i++) {
            dish[i] = Integer.parseInt(bf.readLine());
        }

        // <초밥, 개수>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(dish[i], map.getOrDefault(dish[i], 0) + 1);
        }

        int answer = map.containsKey(c) ? map.size() : map.size() + 1;

        for (int i = k; i < n + k; i++) {
            map.put(dish[i % n], map.getOrDefault(dish[i % n], 0) + 1);

            map.put(dish[(i - k) % n], map.get(dish[(i - k) % n]) - 1);
            if (map.get(dish[(i - k) % n]) == 0) {
                map.remove(dish[(i - k) % n]);
            }

            answer = Math.max(
                    answer,
                    map.containsKey(c) ? map.size() : map.size() + 1
            );
        }

        System.out.println(answer);
    }
}
