import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[2 * n];

        st = new StringTokenizer(bf.readLine());
        
        // 원형 -> 선형으로
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            arr[i] = p;
            arr[i + n] = p;
        }

        int answer = Integer.MAX_VALUE;

        // i: 시작위치, j: 현재 사람이 있는 위치, point: 사람들이 앉아야 하는 위치
        for (int i = 0; i < n; i++) {
            int tmpAnswer = 0;
            int point = 0;
            for (int j = 0; j < n; j++) {
                int people = arr[i + j];
                while (people-- > 0) {
                    tmpAnswer += Math.abs(j - point);
                    point++;
                }
            }

            answer = Math.min(answer, tmpAnswer);
        }

        System.out.println(answer);
    }
}
