import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Lab8B_6A {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        Scanner in = new Scanner(System.in);
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        node8B_6A[] adj = new node8B_6A[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new node8B_6A();
        }
        for (int i = 0; i < m; i++) {
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            adj[tmp2].children.add(adj[tmp1]);
        }
        node8B_6A[] queue = new node8B_6A[n + 1];
        int[] ans = new int[n + 1];
        for (int i = n; i >= 1; --i) {
            if (!adj[i].isVisited) {
                adj[i].val=i;
                int front = 0;
                int rear = 0;
                queue[rear++] = adj[i];
                while (front != rear) {
                    node8B_6A tmp = queue[front++];
                    // ans[tmp] = i;
                    tmp.isVisited = true;
                    for (int j = 0; j < tmp.children.size(); j++) {
                        node8B_6A tmpChild = tmp.children.get(j);
                        if (!tmpChild.isVisited) {
                            queue[rear++] = tmpChild;
                            tmpChild.val=tmp.val;
                            tmpChild.isVisited = true;
                        }
                    }
                }

            }
        }
        for (int i = 1; i <=n; i++) {
            System.out.print(adj[i].val + " ");
        }
        //out.print(ans[n]);
        //out.close();
    }
}

class node8B_6A {
    int val;
    boolean isVisited;
    ArrayList<node8B_6A> children = new ArrayList<>();
}


