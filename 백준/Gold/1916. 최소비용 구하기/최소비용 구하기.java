import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static List<Node>[] graph;

    private static final int MAX = 100_000 * 1_000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());    //  도시 수
        m = Integer.parseInt(bf.readLine());    // 버스 수

        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(bf.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] dist = dijkstra(s);
        
        System.out.println(dist[e]);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAX);

        PriorityQueue<Node> queue = new PriorityQueue<>(
                (n1, n2) -> Integer.compare(n1.cost, n2.cost)
        );

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (dist[cur.city] < cur.cost) {
                continue;
            }

            for (Node next : graph[cur.city]) {
                if (dist[cur.city] + next.cost < dist[next.city]) {
                    dist[next.city] = dist[cur.city] + next.cost;

                    queue.add(new Node(next.city, dist[next.city]));
                }
            }
        }

        return dist;
    }

    private static class Node {
        int city;
        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}
