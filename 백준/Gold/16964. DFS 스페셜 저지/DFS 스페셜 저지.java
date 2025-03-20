import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n; // 정점의 수
    static List<Integer>[] graph;
    static int[] input;

    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        input = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        visit = new boolean[n + 1];
        if (input[0] == 1) {
            visit[1] = true;
            dfs(input[1], 2);
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void dfs(int node, int nextIndex) {
        if (visit[node]) {  // 이미 방문한 경우
            return;
        }

        if (nextIndex == n - 1) {   // 탐색 끝
            return;
        }

        visit[node] = true;

        Set<Integer> set = new HashSet<>();
        for (int next : graph[node]) {
            if (visit[next]) {
                continue;
            }

            set.add(next);
        }

        if (set.size() == 0) { // 탐색 끝
            return;
        }

        if (set.contains(input[nextIndex])) {
            dfs(input[nextIndex], nextIndex + 1);
        } else {
            System.out.println(0);
            System.exit(0);
        }
    }
}
