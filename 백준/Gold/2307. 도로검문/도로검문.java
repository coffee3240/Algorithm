import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int MAX = 1_000_000_000;

    static int n, m;    // 지점 수, 도로 수
    static List<Edge>[] graph;

    static int[] parent;
    static boolean isFirst = true;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        // 다익스트라 수행
        int answer = Integer.MIN_VALUE;
        int target; // 최단 거리의 가중치 합

        parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int[] dist = dijkstra(1, 1);
        isFirst = false;
        target = dist[n];
        if (target == MAX) {
            System.out.println(-1);
            System.exit(0);
        } else {
            int p = n;
            while (p != -1) {
                dist = dijkstra(p, parent[p]);
                if (dist[n] == MAX) {
                    System.out.println(-1);
                    System.exit(0);
                }
                answer = Math.max(answer, dist[n] - target);
                p = parent[p];
            }
        }

        System.out.println(answer);
    }

    private static int[] dijkstra(int node1, int node2) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAX);

        dist[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (dist[now.node] < now.cost) {
                continue;
            }

            for (Edge next : graph[now.node]) {
                if (removeEdge(now.node, next.node, node1, node2) || dist[next.node] < dist[now.node] + next.cost) {
                    continue;
                }

                // parent 배열 다중 업데이트 방지
                if (isFirst) {
                    parent[next.node] = now.node;
                }

                dist[next.node] = dist[now.node] + next.cost;
                pq.add(new Edge(next.node, dist[next.node]));
            }
        }

        return dist;
    }

    private static boolean removeEdge(int now, int next, int n1, int n2) {
        return (now == n1 && next == n2) || (now == n2 && next == n1);
    }

    private static class Edge {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
