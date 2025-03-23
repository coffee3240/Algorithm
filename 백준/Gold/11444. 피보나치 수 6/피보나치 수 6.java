import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
            ret = fibo((x - 1) / 2 + 1) * fibo((x - 1) / 2 + 1) + fibo((x - 1) / 2) * fibo((x - 1) / 2);
        }

        map.put(x, ret % 1_000_000_007);
        return map.get(x);
    }
}
