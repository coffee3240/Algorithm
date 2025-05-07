import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 틀렸던 이유: while 안에서 가장 왼쪽에 있는 알파벳 제거 후 바로 left++ 수행 -> cnt 감소에 필요한 로직에서 오류 발생
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String input = bf.readLine();

        int[] count = new int[26];
        int cnt = 1;    // 사용한 알파벳 종류 수
        int answer = 1; // 정답 길이
        int left = 0;

        count[input.charAt(0) - 'a']++;

        for (int i = 1; i < input.length(); i++) {
            // 처음 등장한 알파벳인 경우 cnt 1 증가
            if (count[input.charAt(i) - 'a'] == 0) {
                cnt++;
            }

            // 알파벳 등장 횟수 증가
            count[input.charAt(i) - 'a']++;

            // n개 이하인 경우에만 길이를 업데이트
            while (cnt > n) {
                // 가장 왼쪽에 있는 알파벳 제거
                count[input.charAt(left) - 'a']--;


                // 제거된 알파벳이 더 이상 사용되지 않는 경우 cnt 1 감소
                if (count[input.charAt(left) - 'a'] == 0) {
                    cnt--;
                }
                
                // 왼쪽 포인터를 1 증가
                left++;
            }

            // 정답 길이 업데이트
            answer = Math.max(answer, i - left + 1);
        }

        System.out.println(answer);
    }
}
