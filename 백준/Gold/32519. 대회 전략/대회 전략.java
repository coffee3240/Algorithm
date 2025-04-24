import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, t; // 서브태스크 개수, 대회 제한 시간
    static int[][] scoreArr; // 서브태스크 배점
    static int[][] timeArr; // 문제 푸는데 걸리는 시간

    static List<ScoreTime>[] stList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // 점수 입력
        scoreArr = new int[3][n];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                scoreArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시간 입력
        timeArr = new int[3][n];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                timeArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        stList = new List[3];
        for (int i = 0; i < 3; i++) {
            stList[i] = new ArrayList<>();
        }

        // 점수, 시간 누적합
        for (int i = 0; i < 3; i++) {
            int totalTime = 0;
            int totalScore = 0;

            // 아무것도 안하고 가만히 있는 경우
            stList[i].add(new ScoreTime(0, 0));

            for (int j = 0; j < n; j++) {
                totalTime += timeArr[i][j];
                totalScore += scoreArr[i][j];

                // 서브태스크를 풀었을 때 점수가 올라가는 경우만 추가
                if (totalScore > stList[i].get(stList[i].size() - 1).score) {
                    stList[i].add(new ScoreTime(totalScore, totalTime));
                }
            }
        }

        int answer = 0;
        for (ScoreTime st0 : stList[0]) {
            // 시간이 부족하여 문제를 풀 수 없는 경우
            if (st0.time > t) {
                continue;
            }

            int remainTime = t - st0.time;

            for (ScoreTime st1 : stList[1]) {
                // 시간이 부족하여 문제를 풀 수 없는 경우
                if (st1.time > remainTime) {
                    continue;
                }

                // 3번 문제에 사용할 수 있는 시간
                int targetTime = remainTime - st1.time;

                // 문제1번과 문제2번의 조합을 바탕으로 문제3번을 탐색
                int score2 = binarySearch(targetTime);

                answer = Math.max(answer, st0.score + st1.score + score2);
            }
        }

        System.out.println(answer);
    }

    // stList는 이미 정렬되어있다.
    // 시간은 항상 양의 정수이므로 오름차순, 점수는 stList를 구성할 때 증가할 때만 추가했으므로 오름차순
    private static int binarySearch(int targetTime) {
        int left = 0;
        int right = stList[2].size();

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (stList[2].get(mid).time <= targetTime) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return stList[2].get(left).score;
    }

    private static class ScoreTime {
        int score;
        int time;

        public ScoreTime(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
}