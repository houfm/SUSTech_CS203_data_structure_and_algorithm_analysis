import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
7 9 6
1 2
2 3
3 1
5 4
4 5
6 3
6 7
7 3
5 7
 */
public class Lab9D {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        node9D[] graph = new node9D[n + 1];
        node9D[] reverse_graph = new node9D[n + 1];
        edge9D[] edges = new edge9D[m];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new node9D(i);
            reverse_graph[i] = new node9D(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            graph[u].children.add(graph[v]);
            reverse_graph[v].children.add(reverse_graph[u]);
            edges[i] = new edge9D(u, v);
        }
        pop_out pop_out = new pop_out(n);
        for (int i = 1; i < n + 1; i++) {
            if (!reverse_graph[i].isVisited) {
                dfs1(reverse_graph,i,pop_out);
            }
        }
        int scc=0;
        // The used scc index is start from 1.
        int cur;
        for (int i = n-1; i >= 0; i--) {
            cur = pop_out.sequence[i];
            if (!graph[cur].isVisited) {
                ++scc;
                dfs2(graph,cur,scc);
            }
        }
        int num_of_scc_has_indegree = 0;
        // store the number of scc that have indegree.
        int[] scc_indegree = new int[scc+1];
        // store whether the scc has indegree or not.
        for (int i = 0; i < m; i++) {
            int from = edges[i].from;
            int to = edges[i].to;
            if ((graph[from].scc != graph[to].scc) && scc_indegree[graph[to].scc] == 0) {
                // There are 2 circumstances that a scc can be regarded as "have indegree".
                // Besides it really has indegree,
                // if the target s in a scc with indegree 0, it can still be regarded as "have indegree".
                num_of_scc_has_indegree++;
                scc_indegree[graph[to].scc] = 1;
                // indegree of the destination plus 1
            }
        }
        if (scc_indegree[graph[s].scc]==0) {
            // If the indegree of the scc of target s is 0
            out.print(scc- num_of_scc_has_indegree-1);
        } else {
            out.print(scc- num_of_scc_has_indegree);
        }
        out.close();
    }

    static void dfs1(node9D[] graph, int cur, pop_out pop_out) {
        // be careful of the traverse of primary data type.
        graph[cur].isVisited = true;
        for (int i = 0; i < graph[cur].children.size(); i++) {
            if (!graph[cur].children.get(i).isVisited) {
                dfs1(graph, graph[cur].children.get(i).index, pop_out);
            }
        }
        pop_out.sequence[pop_out.cnt++] = cur;

    }

    static void dfs2(node9D[] graph, int cur,int scc) {
        graph[cur].isVisited = true;
        graph[cur].scc = scc;
        for (int i = 0; i < graph[cur].children.size(); i++) {
            if (!graph[cur].children.get(i).isVisited) {
                dfs2(graph, graph[cur].children.get(i).index,scc);
            }
        }
    }
}

class node9D {
    int index;
    int scc;
    ArrayList<node9D> children = new ArrayList<node9D>();
    boolean isVisited;

    public node9D(int index) {
        this.index = index;
    }
}

class edge9D {
    int from;
    int to;

    public edge9D(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

class pop_out{
    int cnt;
    int[] sequence;

    public pop_out(int n) {
        this.sequence = new int[n];
    }
}



