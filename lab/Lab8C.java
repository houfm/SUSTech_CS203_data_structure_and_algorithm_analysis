import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lab8C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node8C[] arr = new node8C[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = new node8C();
        }
        for (int i = 0; i < m; i++) {
            // undirected edge;
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            if (tmp1==tmp2){
                out.println("Bad");
                out.close();
                return;
            }
            arr[tmp1].children.add(arr[tmp2]);
            arr[tmp2].children.add(arr[tmp1]);
        }
        node8C[] queue = new node8C[n];
        for (int i = 1; i <= n; i++) {
            if (!arr[i].isVisited) {
                int nodeCnt=0;
                int edgeCnt = 0;
                int front = 0;
                int rear = 0;
                queue[rear++] = arr[i];
                nodeCnt++;
                arr[i].isVisited = true;
                while (front < rear) {
                    node8C temp = queue[front++];
                    edgeCnt += temp.children.size();
                    for (int j = 0; j < temp.children.size(); j++) {
                        node8C child = temp.children.get(j);
                        if (!child.isVisited) {
                            child.isVisited = true;
                            queue[rear++] = child;
                            nodeCnt++;
                        }
                    }
                }
                if (edgeCnt / 2 >= nodeCnt) {
                    out.println("Bad");
                    out.close();
                    return;
                }
            }
        }
        out.println("Good");
        out.close();
    }
}

class node8C {
    boolean isVisited;
    ArrayList<node8C> children = new ArrayList<>();
}


