import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dist = new int[100_001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[n] = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(n);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            int teleport = cur * 2;
            if (teleport <= 100_000 && dist[teleport] > dist[cur]) {
                dist[teleport] = dist[cur];
                dq.addFirst(teleport);
            }

            for (int next : new int[]{cur - 1, cur + 1}) {
                if (next >= 0 && next <= 100_000 && dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1;
                    dq.addLast(next);
                }
            }
        }

        System.out.println(dist[k]);
    }
}
