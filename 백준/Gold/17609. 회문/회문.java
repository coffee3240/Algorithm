import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String input = bf.readLine();

            sb.append(solve(input)).append('\n');
        }
        System.out.println(sb);
    }

    private static int solve(String input) {
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) == input.charAt(right)) {
                left++;
                right--;
            } else {
                if (isPalin(left + 1, right, input) || isPalin(left, right - 1, input)) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }

        return 0;
    }

    private static boolean isPalin(int left, int right, String input) {
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
