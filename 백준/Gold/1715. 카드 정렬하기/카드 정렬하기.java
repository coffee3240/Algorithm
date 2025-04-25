import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(bf.readLine()));
        }

        int answer = 0;
        while (pq.size() != 1) {
            int sum = pq.poll() + pq.poll();
            answer += sum;
            pq.add(sum);
        }

        System.out.println(n == 1 ? 0 : answer);
    }
}