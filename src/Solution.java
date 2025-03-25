class Solution {

    public static int solution(int N, int[][] road, int K) {
        int inf = 10000000;
        boolean[] visit = new boolean[N];
        int[] distance = new int[N];

        // 각 정점에서 다른 이어진 정점 간의 거리 배열
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = inf;
            }
        }

        // road[i][0], road[i][1] 번째 정점간의 거리는 road[i][2]
        for (int[] a : road) {
            // 정점 간의 간선이 2개 이상이 있는 경우도 있기 때문에
            // 중복되는 길이 있을 경우 비용이 더 낮은 값으로 넣어줌
            if (arr[a[0] - 1][a[1] - 1] != inf){
                arr[a[0] - 1][a[1] - 1] = Math.min(arr[a[0] - 1][a[1] - 1], a[2]);
                arr[a[1] - 1][a[0] - 1] = Math.min(arr[a[1] - 1][a[0] - 1], a[2]);
                continue;
            }
            arr[a[0] - 1][a[1] - 1] = a[2];
            arr[a[1] - 1][a[0] - 1] = a[2];
        }

        // 수정 1
        // 수정 2
        // 수정 3

        // 최소거리를 시작지점 1번 마을 기준으로 초기화
        for (int i = 0; i < N; i++) {
            distance[i] = arr[0][i];
        }
        visit[0] = true;
        distance[0] = 0;

        for (int i = 0; i < N; i++) {
            int current = getSmallIndex(N, distance, visit);
            visit[current] = true;

            for (int j = 0; j < N; j++) {
                if (!visit[j] && (distance[current] + arr[current][j]) < distance[j]) {
                    distance[j] = distance[current] + arr[current][j];
                }
            }
        }

        int count = 0;
        for (int d : distance) {
            if (d <= K) {
                count++;
            }
        }

        return count;
    }

    // 아직 방문하지 않은 노드 중에서 가장 도달 비용이 낮은 노드를 반환
    private static int getSmallIndex(int num, int[] distance, boolean[] visit) {
        int min = 100000000;
        int minIndex = 0;

        for (int i = 0; i < num; i++) {
            if (distance[i] < min && !visit[i]) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static int[][] arr2 = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};

    public static void main(String[] args) {
        solution(6, arr2, 4);
    }

}
