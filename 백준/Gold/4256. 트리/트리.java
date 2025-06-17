import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] preorder;
    static int[] inorder;
    static int[] position;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(bf.readLine());    // 노드 개수
            preorder = new int[n];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            inorder = new int[n];
            position = new int[n + 1];  // 중위 순회 노드의 인덱스 저장
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                position[inorder[i]] = i;
            }

            StringBuilder sb = new StringBuilder();
            recur(0, n - 1, 0, n - 1, sb);
            answer.append(sb).append('\n');
        }

        System.out.println(answer);
    }

    private static void recur(int preStart, int preEnd, int inStart, int inEnd, StringBuilder sb) {
        // 탐색할 노드가 없는 경우
        if (preStart > preEnd || inStart > inEnd) {
            return;
        }

        // 전위 순회의 결과에서 루트 노드 찾기
        int root = preorder[preStart];

        // 중위 순회 결과에서 인덱스 찾기 (인덱스 왼쪽은 왼쪽 서브 트리, 오른쪽은 오른쪽 서브 트리)
        int index = position[root];

        // 왼쪽 서브 트리 크기
        int leftTreeSize = index - inStart;

        // 왼쪽 서브 트리 탐색
        recur(preStart + 1, preStart + leftTreeSize, inStart, index - 1, sb);

        // 오른쪽 서브 트리 탐색
        recur(preStart + leftTreeSize + 1, preEnd, index + 1, inEnd, sb);

        // 루트 탐색
        sb.append(root).append(" ");
    }
}
