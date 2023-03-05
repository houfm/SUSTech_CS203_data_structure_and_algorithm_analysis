import java.io.*;
import java.util.StringTokenizer;

public class Lab8B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node8B[] head = new node8B[n + 1];
        node8B[] cur = new node8B[n + 1];
        for (int i = 1; i < n + 1; i++) {
            head[i] = new node8B(i);
            cur[i] = head[i];
        }
        for (int i = 0; i < m; i++) {
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            cur[tmp2].next = new node8B(tmp1);
            cur[tmp2] = cur[tmp2].next;
        }
        int[] isVisited = new int[n + 1];
        int[] ans = new int[n + 1];
        int[] queue = new int[n + 1];
        // initialization of queue should be placed out of the loop.
        for (int i = n; i >= 1; --i) {
            if (isVisited[i] == 0) {
                int front = 0;
                int rear = 0;
                queue[rear++] = i;
                while (front != rear) {
                    int tmp = queue[front++];
                    ans[tmp] = i;
                    isVisited[tmp] = 1;
                    node8B curNode = head[tmp];
                    curNode = curNode.next;
                    while (curNode != null) {
                        if (isVisited[curNode.val]==0){
                            queue[rear++] = curNode.val;
                        }
                        curNode = curNode.next;
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
}

class node8B {
    int val;
    node8B next;

    node8B(int value) {
        this.val = value;
    }
}







