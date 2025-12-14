import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = bf.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Character.getNumericValue(row.charAt(j));
            }
        }

        int answer = 0;
        int total = n * m;

        for (int mask = 0; mask < (1 << total); mask++) {
            int sum = 0;

            for (int i = 0; i < n; i++) {
                int num = 0;
                for (int j = 0; j < m; j++) {
                    int index = i * m + j;
                    if ((mask & (1 << index)) == 0) {
                        num = num * 10 + arr[i][j];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }

            for (int j = 0; j < m; j++) {
                int num = 0;
                for (int i = 0; i < n; i++) {
                    int index = i * m + j;
                    if ((mask & (1 << index)) != 0) {
                        num = num * 10 + arr[i][j];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
