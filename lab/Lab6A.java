import java.util.ArrayList;
import java.util.Scanner;

public class Lab6A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = sc.nextInt();
        node6A[] tree = new node6A[n];
        // create an array storing a tree
        // the left side 'tree' store the address of tree[0],
        // and as the address of tree is continuous,
        // we can find address of all elements by adding some number to the address of tree[0]
        // the right side create a set of joined cells storing the address of each element
        for (int i = 0; i < n; i++) {
            // ??????? i<n not n-1
            tree[i] = new node6A();
            // this statement create a place to store an element, and link it to certain cell
            // to prevent null cursor
        }
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            // do not forget to -1
            int w = sc.nextInt();
            tree[a].children.add(tree[b]);
            tree[a].length.add(w);
            tree[b].children.add(tree[a]);
            tree[b].length.add(w);
            // the length.add must be placed behind the edges.add
            // It is necessary to put a and b into each edges array.
            // eg. 13 24 41
            // It is wrong intuition to think that the father has a smaller index.
        }
        // bfs: using queue
        node6A[] q = new node6A[n];
        // the length is n as every element only in once
        int front = 0;
        int rear = 0;
        q[rear++] = tree[0];
        // as the root number of the tree is 1, we first put the first element in tree into the queue
        tree[0].isvisited = true;
        tree[0].path = 0;
        int cnt = 0;
        while (rear != front) {
            node6A temp = q[front++];
            for (int i = 0; i < temp.children.size(); i++) {
                if (!temp.children.get(i).isvisited) {
                    // go through it only when it has not been visited before
                    temp.children.get(i).path = temp.path + temp.length.get(i);
                    if (temp.children.get(i).path == num && temp.children.get(i).children.size() == 1) {
                        cnt++;
                    }
                    q[rear++] = temp.children.get(i);
                    temp.children.get(i).isvisited = true;
                }

            }
        }
        System.out.println(cnt);
    }

//    static void dfs(node temp){
//        for (int i = 0; i < temp.edges.size(); i++) {
//            if (temp.edges.get(i).isvisited == false){
//                temp.edges.get(i).isvisited=true;
//                temp.edges.get(i).path = temp.path + temp.length.get(i);
//                if(temp.edges.get(i).path==num && temp.edges.get(i).edges.size()==1) cnt++;
//                dfs(temp.edges.get(i));
//            }
//        }
//    }
}

class node6A {
    int path;
    ArrayList<node6A> children = new ArrayList<>();
    ArrayList<Integer> length = new ArrayList<>();
    boolean isvisited;
}
