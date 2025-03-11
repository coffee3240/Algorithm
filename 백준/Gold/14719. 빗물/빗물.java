import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(bf.readLine());
        int height = Integer.parseInt(st.nextToken());
        int max = height;
        stack.push(height);

        int answer = 0;
        for (int i = 1; i < w; i++) {
            int cnt = 0;
            height = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek() < height) {
                answer += Math.min(max, height) - stack.pop();
                cnt++;
            }

            while (cnt-- > 0) {
                stack.push(height);
            }

            stack.push(height);
            max = Math.max(max, height);
        }

        System.out.println(answer);
    }
}
