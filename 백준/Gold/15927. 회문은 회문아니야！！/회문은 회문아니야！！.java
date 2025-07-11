import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        int n = input.length();

        // 회문이 아닌 경우
        for (int i = 0; i < n / 2; i++) {
            if (input.charAt(i) != input.charAt(n - 1 - i)) {
                System.out.println(n);
                return;
            }
        }

        // 회문인 경우
        // 1) 모든 문자가 같은 경우 -1
        // 2) 모든 문자가 같지 않은 경우 n - 1
        boolean all = true;
        for (int i = 1; i < n; i++) {
            if (input.charAt(i) != input.charAt(0)) {
                all = false;
                break;
            }
        }

        System.out.println(all ? -1 : n - 1);
    }
}
