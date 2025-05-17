import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int mod = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        // 일, 오늘 상태, 어제 상태, 그제 상태, 지각 횟수
        int[][][][][] arr = new int[1001][3][3][3][2];

        // 출석: 0, 결석: 1, 지각: 2
        for (int now = 0; now < 3; now++) {
            for (int prev = 0; prev < 3; prev++) {
                for (int prev2 = 0; prev2 < 3; prev2++) {
                    // 결석 연속 3번
                    if (now == prev && prev == prev2 && prev2 == 1) {
                        continue;
                    }

                    // 지각 2번
                    if ((now == 2 && prev == 2) || (now == 2 && prev2 == 2) || (prev == 2 && prev2 == 2)) {
                        continue;
                    }

                    if (now == 2 || prev == 2 || prev2 == 2) {
                        arr[3][now][prev][prev2][1] = 1;
                    } else {
                        arr[3][now][prev][prev2][0] = 1;
                    }
                }
            }
        }

        for (int day = 4; day < n + 1; day++) {
            for (int prev = 0; prev < 3; prev++) {
                for (int prev2 = 0; prev2 < 3; prev2++) {
                    for (int prev3 = 0; prev3 < 3; prev3++) {
                        // 출석
                        // i) 오늘 출석, 지각 0회
                        arr[day][0][prev][prev2][0] += arr[day - 1][prev][prev2][prev3][0];
                        arr[day][0][prev][prev2][0] %= mod;

                        // ii) 오늘 출석, 지각 1회
                        arr[day][0][prev][prev2][1] += arr[day - 1][prev][prev2][prev3][1];
                        arr[day][0][prev][prev2][1] %= mod;

                        // 결석

                        // i) 결석 3번 방지
                        if (!(prev == 1 && prev2 == 1)) {
                            // ii) 오늘 결석, 지각 0회
                            arr[day][1][prev][prev2][0] += arr[day - 1][prev][prev2][prev3][0];
                            arr[day][1][prev][prev2][0] %= mod;

                            // ii) 오늘 결석, 지각 1회
                            arr[day][1][prev][prev2][1] += arr[day - 1][prev][prev2][prev3][1];
                            arr[day][1][prev][prev2][1] %= mod;
                        }


                        // 지각
                        // i) 오늘 지각
                        arr[day][2][prev][prev2][1] += arr[day - 1][prev][prev2][prev3][0];
                        arr[day][2][prev][prev2][1] %= mod;
                    }
                }
            }
        }

        if (n == 0) {
            System.out.println(0);
        } else if (n == 1) {
            System.out.println(3);
        } else if (n == 2) {
            System.out.println(8);
        } else {
            int answer = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 2; l++) {
                            answer += arr[n][i][j][k][l];
                            answer %= mod;
                        }
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
