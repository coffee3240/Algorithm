import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 최대 점수를 구하는 문제가 아님
// 이전에 하던 과제는 항상 바로 직전에 하던 과제
// => 스택?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(bf.readLine());    // 이번 학기의 시간(분)

        int answer = 0;
        Stack<Homework> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int flag = Integer.parseInt(st.nextToken());
            if (flag == 1) {    // 새로운 과제가 들어옴
                // 새로운 과제 생성
                int score = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                Homework homework = new Homework(score, time);

                // 과제 완료 시간 1 차감
                homework.remainTime -= 1;

                if (homework.remainTime == 0) { // 과제 완료
                    answer += homework.score;
                } else {    // 과제 미완료, 스택에 추가
                    stack.add(homework);
                }
            } else {    // 이 시간엔 추가 과제가 없는 경우
                // 현재 풀고있는 과제가 있는 경우
                if (!stack.isEmpty()) {
                    Homework now = stack.pop();

                    // 과제 시간 1 차감
                    now.remainTime -= 1;

                    // 과제 완료
                    if (now.remainTime == 0) {
                        answer += now.score;
                    } else {    // 과제 미완료
                        stack.add(now);
                    }
                }

                // 현재 풀고있는 과제가 없는 경우 (아무 일도 일어나지 않음)
            }
        }

        System.out.println(answer);
    }

    private static class Homework {
        int score;
        int remainTime;

        public Homework(int score, int remainTime) {
            this.score = score;
            this.remainTime = remainTime;
        }
    }
}
