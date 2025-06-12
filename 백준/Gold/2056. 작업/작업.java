import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;   // 작업 수
    static int[] time;  // 작업 완료에 걸리는 시간
    static int[] indegree;  // 진입 간선 수
    static int[] outdegree;
    static List<Integer>[] graph;   // 작업 그래프
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        time = new int[n + 1];
        indegree = new int[n + 1];  // 입력이 1번부터 시작
        outdegree = new int[n + 1];
        graph = new List[n + 1];    // 입력이 1번부터 시작
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;

            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                indegree[i]++;
                int num = Integer.parseInt(st.nextToken());
                outdegree[num]++;
                graph[num].add(i);
            }
        }

        // 진입 간선이 0인 작업을 큐에 넣기
        List<Integer> list = new ArrayList<>();
        answer = new int[n + 1];
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (indegree[i] == 0) {
                queue.add(new Node(i, time[i]));
                answer[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // 작업 완료 시간
            if (outdegree[now.n] == 0) {
                list.add(answer[now.n]);
            }

            // 현재 탐색하는 작업 다음에 수행되어야 하는 작업들을 탐색
            for (int next : graph[now.n]) {
                // 진입 간선 1 감소
                indegree[next]--;

                // next 작업까지 도달했으므로 작업 소요 시간을 업데이트
                answer[next] = Math.max(answer[next], now.t + time[next]);

                // 진입 간선이 0이면 현재 작업 소요 시간이 최소 시간이 됨
                if (indegree[next] == 0) {
                    queue.add(new Node(next, answer[next]));
                }
            }
        }

        // 작업 완료 중 최대 시간이 모든 작업을 마친 최소 시간
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list.get(0));
    }

    private static class Node {
        int n;
        int t;

        public Node(int n, int t) {
            this.n = n;
            this.t = t;
        }
    }
}
