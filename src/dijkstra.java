import java.util.Arrays;

//다익스트라(Dijkstra) 알고리즘
public class dijkstra {

    static int[][] arr;
    static boolean[] visit;
    static int[] distance;
    static int num;
    static int inf = 10000000;

    public static void main(String[] args) {
        visit = new boolean[6];
        distance = new int[6];
        num = 6;

        arr = new int[][]{  {0, 6, inf, 3, 4, inf},
                            {6, 0, 1, 2, inf, 5},
                            {inf, 1, 0, inf, 8, 5},
                            {3, 2, inf, 0, inf, inf},
                            {4, inf, 8, inf, 0, 7},
                            {inf, 5, 5, inf, 7, 0}};

        dijkstra(0); //1번 노드(index:0)에서 시작하여 각 노드로 가는 최소 비용 찾기

        System.out.println(Arrays.toString(distance));

    }

    private static void dijkstra(int start) {
        for (int i = 0; i < num; i++) { //최초 arr 의 값을 distance 에 넣어준다
            distance[i] = arr[start][i];
        }

        visit[start] = true; // 시작하는 노드를 true 로 갱신

        for (int i = 0; i < num; i++) {
            int current = getSmallIndex();
            visit[current] = true;  // 방문하지 않은 노드 중 가장 가까운 노드를 방문

            for (int j = 0; j < num; j++) {
                if (!visit[j] && (distance[current] + arr[current][j] < distance[j])) {
                        //distance[current] : 시작노드에서 current 노드로 가는 (현재까지 갱신된)최소 비용
                        //arr[current][j] : current 노드에서 j 노드로 가는 direct 비용
                        distance[j] = distance[current] + arr[current][j];

                }
            }
        }

    }

    // 아직 방문하지 않은 노드 중에서 가장 비용이 낮은 노드를 반환
    private static int getSmallIndex() {
        int min = inf;
        int minIndex = 0;

        for (int i = 0; i < num; i++) {
            if (distance[i] < min && !visit[i]) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

}