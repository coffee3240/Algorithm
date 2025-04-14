import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static List<Edge> edges;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, cost));
        }

        dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        if (bellmanFord(1)) {
            sb.append(-1);
        } else {
            for (int i = 2; i < n + 1; i++) {
                sb.append(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord(int start) {
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                int from = edge.from;
                int to = edge.to;
                int cost = edge.cost;

                if (dist[from] != Integer.MAX_VALUE && dist[to] > dist[from] + cost) {
                    dist[to] = dist[from] + cost;

                    if (i == n - 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}