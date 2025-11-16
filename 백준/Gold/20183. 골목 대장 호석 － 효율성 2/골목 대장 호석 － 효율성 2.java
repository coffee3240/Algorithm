import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, a, b;
    static long c;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());

        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        long left = 0;
        long right = 1_000_000_000;
        long answer = -1;

        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (dijkstra(mid)) {
                answer = mid;
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(answer);
    }

    private static boolean dijkstra(long targetCost) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (n1, n2) -> Long.compare(n1.cost, n2.cost)
        );

        pq.add(new Node(a, 0));
        dist[a] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.n]) {
                continue;
            }

            if (cur.n == b) {
                break;
            }

            for (Edge next : graph[cur.n]) {
                if (next.cost > targetCost) {
                    continue;
                }

                long nextCost = cur.cost + next.cost;
                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.add(new Node(next.to, nextCost));
                }
            }
        }

        return dist[b] <= c;
    }

    private static class Node {
        int n;
        long cost;

        public Node(int n, long cost) {
            this.n = n;
            this.cost = cost;
        }
    }

    private static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
