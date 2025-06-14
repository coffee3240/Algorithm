import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(bf.readLine());    // 목표 채널
        int m = Integer.parseInt(bf.readLine());    // 고장난 버튼의 개수

        boolean[] broken = new boolean[10]; // 인덱스는 버튼, true이면 고장남
        if (m > 0) {
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }
        }

        // 위, 아래 버튼만 이용한 경우
        int answer = Math.abs(n - 100);

        // 채널 이동 후 위, 아래 버튼 이용
        for (int i = 0; i < 999_999; i++) {
            int length = possible(i, broken);
            if (length > 0) {
                answer = Math.min(answer, Math.abs(i - n) + length);
            }
        }

        // 정답 출력
        System.out.println(answer);
    }

    // 누를 수 있는 채널인지 판단
    // 누를 수 있는 채널이면 눌러야 하는 수 반환
    // 누를 수 없는 채널이면 0 반환
    private static int possible(int channel, boolean[] broken) {
        if (channel == 0) {
            return broken[0] ? 0 : 1;
        }

        // 각 자리 수 마다 누를 수 있는지 판단
        int length = 0;
        while (channel > 0) {
            if (broken[channel % 10]) {
                return 0;
            }
            channel /= 10;
            length++;
        }

        return length;
    }
}
