import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;

    static int[][] arr;

    static boolean[] visited;

    static List[] groupList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());

        arr = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        groupList = new List[100];
        for (int i = 0; i < 100; i++) {
            groupList[i] = new ArrayList<Integer>();
        }

        int groupIdx = 0;
        visited = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                bfs(i, groupIdx++);
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    continue;
                }

                if (arr[i][j] == 1) {
                    continue;
                }

                arr[i][j] = 101;
            }
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < groupList.length; i++) {
            if (groupList[i].isEmpty()) {
                break;
            }

            int min = Integer.MAX_VALUE;
            int minNode = 0;

            for (int j = 0; j < groupList[i].size(); j++) {
                int node1 = (int) groupList[i].get(j);
                int max = 0;
                for (int k = 0; k < groupList[i].size(); k++) {
                    int node2 = (int) groupList[i].get(k);
                    max = Math.max(max, arr[node1][node2]);
                }

                if (max < min) {
                    min = max;
                    minNode = node1;
                }
            }

            ansList.add(minNode);
        }

        Collections.sort(ansList);
        System.out.println(ansList.size());
        for (Integer node : ansList) {
            System.out.println(node);
        }
    }

    private static void bfs(int cur, int groupIdx) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(cur);
        groupList[groupIdx].add(cur);
        visited[cur] = true;

        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for (int i = 1; i < n + 1; i++) {
                if (arr[now][i] == 1 && !visited[i]) {
                    queue.add(i);
                    groupList[groupIdx].add(i);
                    visited[i] = true;
                }
            }
        }
    }
}