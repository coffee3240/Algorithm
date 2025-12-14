import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());   // 손님
        k = Integer.parseInt(st.nextToken());   // 계산대

        PriorityQueue<Counter> counterPQ = new PriorityQueue<>(
                (c1, c2) -> {
                    if (c1.endTime != c2.endTime) {
                        return Long.compare(c1.endTime, c2.endTime);
                    }

                    return Integer.compare(c1.index, c2.index);
                }
        );

        PriorityQueue<Leave> leavePQ = new PriorityQueue<>(
                (l1, l2) -> {
                    if (l1.endTime != l2.endTime) {
                        return Long.compare(l1.endTime, l2.endTime);
                    }

                    return Integer.compare(l2.index, l1.index);
                }
        );

        // 계산대 초기화
        for (int i = 1; i < k + 1; i++) {
            counterPQ.add(new Counter(0, i));
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Counter cur = counterPQ.poll();
            long curEndTime = cur.endTime + w;
            leavePQ.add(new Leave(curEndTime, cur.index, id));

            cur.endTime = curEndTime;
            counterPQ.add(cur);
        }

        long answer = 0;
        long num = 1;

        while (!leavePQ.isEmpty()) {
            Leave cur = leavePQ.poll();
            answer += (num++ * cur.id);
        }

        System.out.println(answer);
    }

    private static class Counter {
        long endTime;
        int index;

        public Counter(long endTime, int index) {
            this.endTime = endTime;
            this.index = index;
        }
    }

    private static class Leave {
        long endTime;
        int index;
        int id;

        public Leave(long endTime, int index, int id) {
            this.endTime = endTime;
            this.index = index;
            this.id = id;
        }
    }
}
