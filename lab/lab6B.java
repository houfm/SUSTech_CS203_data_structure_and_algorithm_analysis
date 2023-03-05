import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lab6B {
    // if there are two edges, then there need a break point
    // and there may be grand edges
    // first we can know the time arriving 1 without effect
    // meet if and only if the time is same
    // using bfs we can get sorted edges tree

    // c
    // root: no
    // relationship: no
    // binary tree: not know
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node6B[] tree = new node6B[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node6B();
        }
        for (int i = 0; i < n-1; i++) {
            // n is the number of nodes NOT the number of edges
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            // do not forget to -1
            tree[v].children.add(tree[u]);
            tree[u].children.add(tree[v]);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int hasGiant = in.nextInt() - 1;
            // do not forget to -1
            tree[hasGiant].hasGiant = true;
        }
        node6B[] level1 = new node6B[tree[0].children.size()];
        for (int i = 0; i < level1.length; i++) {
            level1[i] = new node6B();
            tree[0].children.get(i).level = 1;
            tree[0].children.get(i).realSteps =1;
            level1[i] = tree[0].children.get(i);
            tree[0].children.get(i).isVisited = true;
        }
        int max = 1;
        for (int i = 0; i < level1.length; i++) {
            int previous = 1;
            // in every loop, the previous should be renewed
            node6B[] queue = new node6B[n];
            int front = 0;
            int rear = 0;
            queue[rear++] = level1[i];
            while (rear != front) {
                node6B temp = queue[front++];
                for (int j = 0; j < temp.children.size(); j++) {
                    if (!temp.children.get(j).isVisited) {
                        // As we do not know the relationship between nodes,
                         // all execute is based on the fact that the node is not visited
                        temp.children.get(j).level = temp.level+1;
                        if (temp.children.get(j).hasGiant){
                            if (previous >= temp.children.get(j).level) {
                                temp.children.get(j).realSteps = previous+1;
                            } else {
                                temp.children.get(j).realSteps = temp.children.get(j).level;
                            }
                            previous = temp.children.get(j).realSteps;
                            max = Math.max(max, temp.children.get(j).realSteps);
                        }
                        queue[rear++] = temp.children.get(j);
                        temp.children.get(j).isVisited = true;
                        // do not forget to flag it
                    }
                }
            }
        }
        out.println(max);
        out.close();
    }
}

class node6B {
    ArrayList<node6B> children = new ArrayList<>();
    boolean hasGiant;
    int level;
    int realSteps;
    boolean isVisited;
    // as we do not know the relationship between nodes, we use this variable
    // to manage going through every node for only once
}



