import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Stat[] stats;
    static Match[] matches;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        matches = new Match[15];
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[index] = new Match(i, j);
                index++;
            }
        }

        int t = 4;
        while (t-- > 0) {
            stats = new Stat[6];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < 6; i++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                stats[i] = new Stat(win, draw, lose);
            }

            if (isValidStats()) {
                sb.append(dfs(0) ? 1 : 0).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static boolean dfs(int index) {
        if (index == matches.length) {
            return true;
        }

        int home = matches[index].home;
        int away = matches[index].away;

        // home 승
        if (stats[home].win > 0 && stats[away].lose > 0) {
            stats[home].win--;
            stats[away].lose--;

            if (dfs(index + 1)) {
                return true;
            }

            stats[home].win++;
            stats[away].lose++;
        }

        // away 승
        if (stats[home].lose > 0 && stats[away].win > 0) {
            stats[home].lose--;
            stats[away].win--;

            if (dfs(index + 1)) {
                return true;
            }

            stats[home].lose++;
            stats[away].win++;
        }

        // 무승부
        if (stats[home].draw > 0 && stats[away].draw > 0) {
            stats[home].draw--;
            stats[away].draw--;

            if (dfs(index + 1)) {
                return true;
            }

            stats[home].draw++;
            stats[away].draw++;
        }

        return false;
    }

    private static boolean isValidStats() {
        int totalWin = 0;
        int totalLose = 0;
        int totalDraw = 0;

        for (Stat stat : stats) {
            totalWin += stat.win;
            totalLose += stat.lose;
            totalDraw += stat.draw;

            if (stat.win + stat.lose + stat.draw != 5) {
                return false;
            }
        }

        if (totalWin != totalLose
                || totalDraw % 2 != 0
                || totalWin > 15
                || totalLose > 15
                || totalDraw > 30
        ) {
            return false;
        }

        return true;
    }

    private static class Match {
        int home;
        int away;

        public Match(int home, int away) {
            this.home = home;
            this.away = away;
        }
    }

    private static class Stat {
        int win;
        int draw;
        int lose;

        public Stat(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }
}
