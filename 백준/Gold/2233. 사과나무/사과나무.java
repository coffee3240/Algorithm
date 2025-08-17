import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int n;

    // 조상의 최대 높이
    static int k = 0;

    static int[] level;
    static int[][] parent;

    // 노드 번호 -> 0, 1 위치
    static int[] openIdx, closeIdx;

    // 위치 -> 노드 번호
    static int[] pos2node;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        String input = bf.readLine();

        // 조상 최대 높이 구하기
        initK();

        openIdx = new int[n + 1];
        closeIdx = new int[n + 1];
        pos2node = new int[2 * n + 1];
        level = new int[n + 1];
        parent = new int[n + 1][k];

        Stack<Integer> stack = new Stack<>();
        int index = 1;

        for (int i = 1; i <= input.length(); i++) {
            char ch = input.charAt(i - 1);

            if (ch == '0') {
                openIdx[index] = i;
                pos2node[i] = index;

                parent[index][0] = stack.isEmpty() ? 0 : stack.peek();
                level[index] = parent[index][0] == 0 ? 1 : level[parent[index][0]] + 1;

                stack.push(index);
                index++;
            } else if (ch == '1') {
                int node = stack.pop();
                closeIdx[node] = i;
                pos2node[i] = node;
            }
        }

        // 부모 노드 초기화
        initParent();

        st = new StringTokenizer(bf.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        x = pos2node[x];
        y = pos2node[y];

        // 최소 공통 조상
        int node = lca(x, y);
        System.out.println(openIdx[node] + " " + closeIdx[node]);
    }

    private static int lca(int x, int y) {
        // 항상 x가 y보다 밑에 있도록 조정
        if (level[x] < level[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        // x와 y의 레벨 맞추기
        for (int i = k - 1; i > -1; i--) {
            if (level[x] - level[y] >= (1 << i)) {
                x = parent[x][i];
            }
        }

        if (x == y) {
            return x;
        }

        for (int i = k - 1; i > -1; i--) {
            if (parent[x][i] != parent[y][i]) {
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        return parent[x][0];
    }

    private static void initParent() {
        for (int i = 1; i < k; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static void initK() {
        int tmp = 1;
        while (tmp <= n) {
            tmp = tmp << 1;
            k++;
        }
    }
}
