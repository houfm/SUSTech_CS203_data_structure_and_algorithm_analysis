import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lab9C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        // k: total number of colors
        int c = in.nextInt();
        node9C[] node = new node9C[n + 1];
        node9C[][] queue = new node9C[k + 1][n + 1];
        int[] front = new int[k + 1];
        int[] rear = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            int color = in.nextInt();
            node[i] = new node9C(color, k);
            queue[color][rear[color]++] = node[i];
            node[i].isVisited[color] = true;
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            node[u].children.add(node[v]);
            node[v].children.add(node[u]);
        }
        int current_front;
        int current_rear;
        node9C[] current_queue;
        for (int i = 1; i < k + 1; i++) {
            current_queue = queue[i];
            current_front = front[i];
            current_rear = rear[i];
            while (current_rear != current_front) {
                node9C tmp = current_queue[current_front++];
                for (int j = 0; j < tmp.children.size(); j++) {
                    node9C tmp_child = tmp.children.get(j);
                    if (!tmp_child.isVisited[i]) {
                        tmp_child.cost[i] = tmp.cost[i] + 1;
                        current_queue[current_rear++] = tmp_child;
                        tmp_child.isVisited[i] = true;
                    }
                }
            }
        }
        int[] tmp = new int[k+1];
        int cnt;
        for (int i = 1; i < n + 1; i++) {
            cnt=0;
            node[i].mergesort_cost(tmp,1,k);
            for (int j = 1; j <= c; j++) {
                // Be careful about the index of the cost to be added.
                 cnt+=node[i].cost[j];
            }
            if (i!=n) {
                out.print(cnt+" ");
            } else {
                out.print(cnt);
            }

        }
        out.close();
    }

}

class node9C {
    int color;
    int[] cost;
    boolean[] isVisited;
    ArrayList<node9C> children = new ArrayList<>();

    public node9C(int color, int k) {
        this.color = color;
        cost = new int[k + 1];
        isVisited = new boolean[k + 1];
    }

    void mergesort_cost(int[] tmp, int min, int max) {
        if (min < max) {
            int mid = min + (max - min) / 2;
            mergesort_cost(tmp, min, mid);
            mergesort_cost(tmp, mid + 1, max);
            merge(tmp, min, mid, max);
        }
    }

    void merge(int[] tmp, int min, int mid, int max) {
        int cur1 = min;
        int cur2 = mid + 1;
        int curTmp = min;
        while (cur1 <= mid && cur2 <= max) {
            if (cost[cur1] < cost[cur2]) {
                tmp[curTmp++] = cost[cur1++];
            } else {
                tmp[curTmp++] = cost[cur2++];
            }
        }
        while (cur1 <= mid) {
            tmp[curTmp++] = cost[cur1++];
        }
        while (cur2 <= max) {
            tmp[curTmp++] = cost[cur2++];
        }
        for (int i = min; i <= max; i++) {
            cost[i] = tmp[i];
        }
    }
}

