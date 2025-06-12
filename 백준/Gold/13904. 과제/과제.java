import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static Homework[] homeworks;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        homeworks = new Homework[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            homeworks[i] = new Homework(d, w);
        }

        // Homework 정렬
        // 과제 마감일이 적은 순(과제 마감이 임박한 순), 점수가 높은 순
        Arrays.sort(homeworks, (h1, h2) -> {
            if (h1.d == h2.d) {
                return Integer.compare(h2.w, h1.w);
            }

            return Integer.compare(h1.d, h2.d);
        });

        // 풀 수 있는 과제만 들어있음
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            // pq.size()는 끝낸 과제 수
            // 하루에 하나의 과제만 끝낼 수 있으므로 만약, pq.size()가 3이고 homeworks[i].d가 4이면
            // 과제는 3개했고 현재 해야하는 과제는 4일남았으므로 1일을 더 투자해서 과제를 할 수 있다.
            if (pq.size() < homeworks[i].d) {
                pq.add(homeworks[i].w);
                answer += homeworks[i].w;
            } else {
                // pq.size()가 3이고 homeworks[i].d가 3이면 이미 과제 3개를 하는 데에 3일을 사용했으므로 과제를 못한다.
                // 이전에 했던 과제의 점수가 10이고 현재 하려는 과제가 30점이라면
                // 이전에 했던 과제를 수행하지않고 그 날에 현재 과제를 하면 더 큰 점수를 얻을 수 있다. (과제를 빨리 하는건 문제가 되지 않으므로)
                if (pq.peek() < homeworks[i].w) {
                    answer -= pq.poll();
                    pq.add(homeworks[i].w);
                    answer += homeworks[i].w;
                }
            }
        }

        System.out.println(answer);
    }

    private static class Homework {
        int d;  // 과제 마감일까지 남은 일수
        int w;  // 과제 점수

        public Homework(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
}
