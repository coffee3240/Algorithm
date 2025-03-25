import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String a = bf.readLine();
        String b = bf.readLine();

        int[][] arr = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }

        System.out.println(arr[arr.length - 1][arr[0].length - 1]);

        int i = a.length();
        int j = b.length();

        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (arr[i][j] == arr[i - 1][j]) {
                i--;
            } else if (arr[i][j] == arr[i][j - 1]){
                j--;
            } else {
                sb.insert(0, a.charAt(i - 1));
                i--;
                j--;
            }
        }

        System.out.println(sb);
    }
}
