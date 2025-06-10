import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(bf.readLine());
            nodes = new Node[n + 2];
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(bf.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                nodes[i] = new Node(y, x);
            }

            boolean[][] canGo = new boolean[n + 2][n + 2];

            // 초기화
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    if (getDist(i, j) <= 1000) {
                        canGo[i][j] = true;
                    }
                }
            }

            // 플로이드 워셜
            for (int k = 0; k < n + 2; k++) {
                for (int i = 0; i < n + 2; i++) {
                    for (int j = 0; j < n + 2; j++) {
                        if (canGo[i][k] && canGo[k][j]) {
                            canGo[i][j] = true;
                        }
                    }
                }
            }

            // 정답 저장
            sb.append(canGo[0][n + 1] ? "happy" : "sad").append('\n');
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 두 노드 간 거리 (맨하튼)
    private static int getDist(int a, int b) {
        Node nodeA = nodes[a];
        Node nodeB = nodes[b];

        return Math.abs(nodeA.y - nodeB.y) + Math.abs(nodeA.x - nodeB.x);
    }

    // 좌표 저장 객체
    private static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
