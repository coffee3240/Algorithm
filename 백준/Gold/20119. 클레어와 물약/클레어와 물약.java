import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static List<Integer>[] graph;

    static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());   // 물약 종류
        m = Integer.parseInt(st.nextToken());   // 레시피 수

        // 물약 - [레시피1, 레시피2, ... ]
        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] needCount = new int[m];   // 레시피별 남은 재료 수
        int[] result = new int[m];  // 레시피 결과

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int k = Integer.parseInt(st.nextToken());
            needCount[i] = k;

            for (int j = 0; j < k; j++) {
                int a = Integer.parseInt(st.nextToken());
                graph[a].add(i);
            }

            result[i] = Integer.parseInt(st.nextToken());
        }

        l = Integer.parseInt(bf.readLine());    // 클레어가 가지고 있는 물약 종류
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[n + 1];   // 물약을 가지고 있는지
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < l; i++) {
            int a = Integer.parseInt(st.nextToken());
            visit[a] = true;
            queue.add(a);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer c : graph[cur]) {
                needCount[c]--;

                // 특정 물약을 만들 수 있는 경우
                if (needCount[c] == 0) {
                    int make = result[c];
                    if (!visit[make]) {
                        visit[make] = true;
                        queue.add(make);
                    }
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < n + 1; i++) {
            if (visit[i]) {
                pq.add(i);
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(pq.size()).append('\n');
        for (Integer i : pq) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }
}
