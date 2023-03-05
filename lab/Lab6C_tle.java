import java.io.*;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.StringTokenizer;

public class Lab6C_tle {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node6C[] input = new node6C[n + 1];
        for (int i = 1; i < n + 1; i++) {
            input[i] = new node6C();
            input[i].num = i;
        }
        for (int i = 1; i < n; i++) {
            // Although the loop starts from 1, it still should be <1 as n represents the number of nodes
            // but the number of edges is n-1 rather than n.
            int a = in.nextInt();
            int b = in.nextInt();
            input[a].children.add(input[b]);
            input[b].children.add(input[a]);

        }
        int m = in.nextInt();
        constraints[] con = new constraints[m];
        for (int i = 0; i < m; i++) {
            con[i] = new constraints();
        }
        for (int i = 0; i < m; i++) {
            con[i].A = in.nextInt();
            con[i].B = in.nextInt();
            con[i].nA = in.nextInt();
        }
        node6C[] tree = buildTree(input, n);
        getSubNode(tree[1]);
        int ans = binarySearch(tree, con, 0, n);
        out.println(ans);
        out.close();
    }

    static node6C[] buildTree(node6C[] input, int n) {
        // if you want to return an array, it is not necessary to name it.
        node6C[] output = new node6C[n + 1];
        for (int i = 1; i < n + 1; i++) {
            output[i] = new node6C();
            output[i].num = i;
        }
        int front = 0;
        int rear = 0;
        node6C[] queue = new node6C[n];
        queue[rear++] = input[1];
        input[1].isVisited = true;
        input[1].level = 0;
        output[1].level = 0;
        while (rear != front) {
            node6C tmp = queue[front++];
            for (int i = 0; i < tmp.children.size(); i++) {
                if (!tmp.children.get(i).isVisited) {
                    queue[rear++] = tmp.children.get(i);
                    tmp.children.get(i).isVisited = true;
                    tmp.children.get(i).level = tmp.level + 1;
                    output[tmp.num].children.add(output[tmp.children.get(i).num]);
                    output[tmp.children.get(i).num].level = tmp.children.get(i).level;
                    // ??????
                }
            }
        }

        return output;
    }

    static int getSubNode(node6C node) {
        // the subNodes does NOT include the current node
        if (node.children.size() != 0) {
            int tmp = 0;
            for (int i = 0; i < node.children.size(); i++) {
                tmp += getSubNode(node.children.get(i));
            }
            node.subNodes = tmp + 1;
            return node.subNodes;
        } else {
            // When the node is a leaf node, we do not maintain its subNodes value.
            // Instead, we just return 1.
            node.subNodes = 1;
            return 1;
        }
    }


    static int binarySearch(node6C[] tree, constraints[] con, int min, int max) {
        if (min < max) {
            // ? min > max ?????????????????
            int mid = min + (max - min) / 2;
            // when left = mid, the mid should be added 1;
            if (check(mid, tree, con)) {
                return binarySearch(tree, con, min, mid);
            } else {
                return binarySearch(tree, con, mid + 1, max);
            }
        } else {
            if (check(min, tree, con)) {
                return min;
            } else {
                return -1;
            }
        }
    }

    static boolean check(int k, node6C[] tree, constraints[] con) {
        // as the check method is called more than once, the max and min value is changes more than once,
        // but we want it to be the original value every time the check method is called
        int max, min;
        tree = assignExtremes(tree, con, k);
        if (tree[0].min == -1) {
            return false;
        }
        int[] extreme = extremeFind(tree[1]);
        if (extreme[0] == -1) {
            return false;
        } else {
            min = extreme[0];
            max = extreme[1];
        }
        return k >= min && k <= max;
    }

    static node6C[] assignExtremes(node6C[] tree, constraints[] con, int k) {
        // as k is the mid in binary search,
        // the method cannot be written in main method
        int rear = 0, front = 0;
        node6C[] queue = new node6C[tree.length + 1];
        queue[rear++] = tree[1];
        tree[1].min = 0;
        tree[1].max = tree[1].subNodes;
        while (rear != front) {
            node6C tmp = queue[front++];
            for (int i = 0; i < tmp.children.size(); i++) {
                tmp.children.get(i).min = 0;
                tmp.children.get(i).max = tmp.children.get(i).subNodes;
                queue[rear++] = tmp.children.get(i);
            }
        }
        tree[0] = new node6C();
        // Whether the min is assigned to -1 or not, we will check min in the check method,
        // so the initialization should be placed before the foreach loop.
        for (constraints constraints : con) {
            // use foreach to replace for
            int a = constraints.A;
            int b = constraints.B;
            if (constraints.nA > k) {
                tree[0].min = -1;
                // be careful about the index.
                // First make it a ??????
                return tree;
            }
            if (tree[a].level < tree[b].level) {
                if (k - constraints.nA < tree[b].min) {
                    tree[0].min = -1;
                    return tree;
                }
                tree[b].max = Math.min(tree[b].max, k - constraints.nA);
                if (tree[b].max < tree[b].min) {
                    tree[0].min = -1;
                    return tree;
                }
            } else {
                if (constraints.nA > tree[a].max) {
                    tree[0].min = -1;
                    return tree;
                }
                tree[a].min = Math.max(tree[a].min, constraints.nA);
                if (tree[a].min > tree[a].max) {
                    tree[0].min = -1;
                    return tree;
                }
            }
        }
        return tree;
    }


    static int[] extremeFind(node6C node) {
        int[] extreme = new int[2];
        // 0 is min, 1 is max
        if (node.children.size() != 0) {
            int min = 0;
            int max = 0;
            for (int i = 0; i < node.children.size(); i++) {
                int[] tmp = extremeFind(node.children.get(i));
                if (tmp[0]==-1) {
                    extreme[0]=-1;
                    return extreme;
                }
                min += tmp[0];
                max += tmp[1];
            }
            // from above loop we get 2 intervals:[min, max+1], [node.min, node.max]
            if (max + 1 < node.min || node.max < min) {
                extreme[0] = -1;
                return extreme;
            } else {
                extreme[0] = Math.max(min, node.min);
                extreme[1] = Math.min(max + 1, node.max);
            }
        } else {
            if (node.min > node.max) {
                extreme[0] = -1;
                return extreme;
            } else {
                extreme[0] = node.min;
                extreme[1] = node.max;
            }

        }
        return extreme;
    }


}


class node6C {
    int num;
    int min;
    int max;
    int min0;
    int max0;
    int subNodes;
    int level;
    // start from 0
    ArrayList<node6C> children = new ArrayList<>();
    boolean isVisited;
    // use array list rather than array to store edges
    boolean minHasChanged;
    boolean maxHasChanged;

}

class constraints {
    int A;
    int B;
    int nA;
}






