import java.util.ArrayList;

public class Lab8B_arrayList {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int n, m;
        n = in.nextInt();
        m = in.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            addEdge(adj, tmp2, tmp1);
        }
        int[] ans = new int[n + 1];
        int[] isVisited = new int[n + 1];
        int[] queue = new int[n];
        for (int i = n; i >= 1; --i) {
            if (isVisited[i]==0){
                int front = 0;
                int rear = 0;
                queue[rear++] = i;
                while (rear != front) {
                    int tmp = queue[front++];
                    ans[tmp]=i;
                    isVisited[tmp] = 1;
                    for (int j = 0; j < adj.get(tmp).size(); j++) {
                        int v = adj.get(tmp).get(j);
                        if (isVisited[v]==0){
                            queue[rear++] = v;
                        }
                    }
                }
            }
        }
        for (int i = 1; i < n; i++) {
            out.print(ans[i] + " ");
        }
        out.print(ans[n]);
        out.close();
    }

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int v, int w) {
        // The input is directed edge.
        adj.get(v).add(w);
    }
}
