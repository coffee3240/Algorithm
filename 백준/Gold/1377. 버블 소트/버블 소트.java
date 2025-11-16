import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(bf.readLine());
            nodes[i] = new Node(value, i);
        }

        Arrays.sort(nodes, (n1, n2) -> Integer.compare(n1.value, n2.value));

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, nodes[i].index - i);
        }

        System.out.println(answer + 1);
    }

    private static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
