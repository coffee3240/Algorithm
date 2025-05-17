import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 1. 선형탐색
 * - 입력이 (1 0)이 반복되면 O(N * Q)
 * - 시간초과
 *
 * 2. 이분탐색
 * - 정렬되어있지만 꽂혀있거나(T), 아니거나(F) 2가지 값이 존재
 * - 어떻게 탐색하는지 모르겠음
 *
 * 3. TreeSet
 * - ceiling(i) : i보다 크거나 값은 값 중 가장 작은 값, logN
 * - 만약 i번 포트에 전자기기가 꽂혀 있다면, 현재 남아 있는 최소 전력 이상의 포트 중 가장 전력이 작은 포트에 기기를 꽂는다.
 */
public class Main {

    static int n, q;    // 포트 수, 행동 수
    static TreeSet<Integer> emptyPort;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // 처음엔 모든 포트가 비어있음
        emptyPort = new TreeSet<>();
        for (int i = 1; i < n + 1; i++) {
            emptyPort.add(i);
        }

        int[] arr = new int[n + 1]; // 1부터

        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(st.nextToken());
            int port = Integer.parseInt(st.nextToken());

            count++;

            if (type == 1) {
                // 비어있는 경우
                if (emptyPort.contains(port)) {
                    emptyPort.remove(port);

                    // 해당 포트가 몇 번째 행동에 의해 꽂힌건지 저장
                    arr[port] = count;

                    sb.append(port).append('\n');
                } else {
                    // 이미 port가 사용중
                    // 없으면 null 반환
                    Integer p = emptyPort.ceiling(port);
                    if (p == null) {   // 사용 가능한 포트가 없는 경우
                        sb.append(-1).append('\n');
                    } else {
                        // 사용 가능한 포트가 있는 경우
                        emptyPort.remove(p);
                        arr[p] = count;
                        sb.append(p).append('\n');
                    }
                }
            } else if (type == 2) {
                // 꽂혀있지 않은 경우
                if (arr[port] == 0) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(arr[port]).append('\n');

                    // 해당 포트의 충전기 뽑기
                    arr[port] = 0;

                    // 비어있는 포트에 추가
                    emptyPort.add(port);
                }
            }
        }

        System.out.println(sb);
    }
}
