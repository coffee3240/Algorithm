import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine());
        st.nextToken();
        for (int i = 1; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            tree[parent].add(i);
        }

        System.out.println(calc(0));
    }

    private static int calc(int node) {
        // 리프 노드
        if (tree[node].isEmpty()) {
            return 0;
        }

        // 자식 노드이 걸리는 시간 계산
        List<Integer> times = new ArrayList<>();
        for (int child : tree[node]) {
            times.add(calc(child));
        }

        // 내림차순
        Collections.sort(times, Collections.reverseOrder());

        // 각 직속 부하들에게 전화를 하는 로직
        // 직속 부하들이 자신들의 부하들에게 전화를 돌리는 시간이 큰 부하부터 전화를 건다.
        int ret = 0;
        for (int i = 0; i < times.size(); i++) {
            ret = Math.max(ret, i + 1 + times.get(i));
        }

        return ret;
    }
}
