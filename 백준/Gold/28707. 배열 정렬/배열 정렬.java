import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백트래킹 -> 가지치기 조건(swap 시 큰 수가 앞으로 오지 않도록?)
 * 초기(1, 3, 4, 2) -> 3, 4 swap + 2, 4 swap 비용이 3, 2 swap 비용보다 저렴할 수 있음.
 *
 * 다익 -> 배열 상태(노드) + swap 연산(간선) + swap 비용(가중치) ?
 * 
 * 문자열로 상태관리 시 두 자리 숫자 고려안했었음. 예) 1, 2, 10 -> 1210 -> 1, 21, 0 으로 해석이 가능
 */
public class Main {

    static int n;
    static int[] start;
    static int m;
    static Swap[] swaps;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        start = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            start[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(bf.readLine());
        swaps = new Swap[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            swaps[i] = new Swap(
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()));
        }

        // 목표 생성
        int[] target = start.clone();
        Arrays.sort(target);

        System.out.println(sol(start, target));

    }

    private static int sol(int[] start, int[] target) {
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (n1, n2) -> Integer.compare(n1.cost, n2.cost)
        );

        String startStr = Arrays.toString(start);
        String targetStr = Arrays.toString(target);

        pq.add(new Node(start, 0));
        dist.put(startStr, 0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            String curStr = Arrays.toString(cur.state);

            // 같은 상태를 더 좋은 비용으로 방문한 케이스가 있는 경우
            if (cur.cost > dist.get(curStr)) {
                continue;
            }

            // 목표 상태 도달
            if (curStr.equals(targetStr)) {
                return cur.cost;
            }

            for (Swap swap : swaps) {
                int[] nextState = cur.state.clone();

                int tmp = nextState[swap.l];
                nextState[swap.l] = nextState[swap.r];
                nextState[swap.r] = tmp;

                String nextStr = Arrays.toString(nextState);
                int nextCost = cur.cost + swap.c;

                // 기본값 0으로 두었었음. 최소값 찾기니까 최대값으로 설정
                if (dist.getOrDefault(nextStr, Integer.MAX_VALUE) > nextCost) {
                    dist.put(nextStr, nextCost);
                    pq.add(new Node(nextState, nextCost));
                }
            }
        }

        return -1;
    }

    private static class Node {
        int[] state;
        int cost;

        public Node(int[] state, int cost) {
            this.state = state;
            this.cost = cost;
        }
    }

    private static class Swap {
        int l;
        int r;
        int c;

        public Swap(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }
}
