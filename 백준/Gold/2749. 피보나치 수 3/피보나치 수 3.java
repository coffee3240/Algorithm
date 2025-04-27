import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * n이 엄청 크다
 * 시간제한은 1초
 * logN만 통과
 * => 절반씩 나누어서 계산(분할정복...?)
 * => n이 짝수일 때, n이 홀수일 때로 나누면 될 듯(숫자인데 2개로 나누는 방법)
 */
public class Main {
    static long n;
    static Map<Long, Long> map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(bf.readLine());

        map = new HashMap<>();

        System.out.println(fibo(n));
    }

    private static long fibo(long x) {
        if (x == 0) {
            return 0L;
        }

        if (x == 1) {
            return 1L;
        }

        if (x == 2) {
            return 1L;
        }

        if (map.containsKey(x)) {
            return map.get(x);
        }

        long ret;
        if (x % 2 == 0) {
            ret = fibo(x / 2) * (fibo(x / 2) + 2 * fibo(x / 2 - 1));
        } else {
            ret = fibo((x + 1) / 2) * fibo((x + 1) / 2) + fibo((x - 1) / 2) * fibo((x - 1) / 2);
        }

        map.put(x, ret % 1_000_000);
        return map.get(x);
    }
}
