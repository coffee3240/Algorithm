import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static String[] names;
    static Map<String, Integer> index = new HashMap<>();

    static List<Integer>[] graph;
    static List<Integer>[] child;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        names = new String[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            names[i] = st.nextToken();
        }
        Arrays.sort(names);

        for (int i = 0; i < n; i++) {
            index.put(names[i], i);
        }

        graph = new List[n];
        child = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            child[i] = new ArrayList<>();
        }

        m = Integer.parseInt(bf.readLine());
        int[] indegree = new int[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            String name1 = st.nextToken();
            String name2 = st.nextToken();

            int c = index.get(name1);
            int p = index.get(name2);

            graph[p].add(c);
            indegree[c]++;
        }

        Queue<Integer> deque = new ArrayDeque<>();
        List<Integer> roots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                deque.add(i);
                roots.add(i);
            }
        }

        while (!deque.isEmpty()) {
            int now = deque.poll();

            for (int next : graph[now]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    child[now].add(next);
                    deque.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(roots.size()).append('\n');
        for (Integer root : roots) {
            sb.append(names[root]).append(' ');
        }
        sb.append('\n');

        for (int i = 0; i < n; i++) {
            Collections.sort(child[i]);

            sb.append(names[i]).append(' ');
            sb.append(child[i].size()).append(' ');
            for (Integer child : child[i]) {
                sb.append(names[child]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
