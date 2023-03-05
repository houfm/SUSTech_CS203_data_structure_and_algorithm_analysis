import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lab6D {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int max_p = -1;
        int index_of_max;
        int n = in.nextInt();
        long ans;
        // using long
        node6D[] input = new node6D[n + 1];
        for (int i = 0; i < n + 1; i++) {
            input[i] = new node6D();
            input[i].num = i;
        }
        for (int i = 1; i < n; i++) {
            // it is just the number of edges rather than nodes
            int a = in.nextInt();
            int b = in.nextInt();
            input[a].children.add(input[b]);
            input[b].children.add(input[a]);
        }
        int pMax = 0;
        int pMaxNum = 0;
        for (int i = 1; i < n + 1; i++) {
            input[i].p = in.nextInt();
            if (input[i].p > pMax) {
                pMax = input[i].p;
                pMaxNum = i;
            }
        }
        node6D[] tree = createTree(input, n, pMaxNum);
        // obtain the real tree
        findSubMax(tree[pMaxNum]);
        // we want to find the subMax of all the nodes, but as if we want to get the subMax of higher level,
        // we need to first get the subMax of lower level
        // So when we get the subMax of the highest level, we will get the subMax of ALL level
        int cnt = 0;
        int max1 = 0, max2 = 0;
        for (int i = 0; i < tree[pMaxNum].children.size(); i++) {
            node6D tmp = tree[pMaxNum].children.get(i);
            if (tmp.pMax_of_subtree >= pMax) {
                cnt++;
            }
            if (tmp.pMax_of_subtree > max1) {
                max2 = max1;
                max1 = tmp.pMax_of_subtree;
            } else if (tmp.pMax_of_subtree > max2) {
                max2 = tmp.pMax_of_subtree;
            }
        }
        ans = calculateE(tree, pMaxNum, n);
        if (tree[pMaxNum].children.size() == 1) {
            ans += pMax;
            if (cnt < 1) {
                ans = ans + pMax - max1;
            }
        } else if (cnt < 2) {
            ans = ans + 2L * pMax - max1 - max2;
        }
        out.println(ans);
        out.close();
    }

    static node6D[] createTree(node6D[] input, int n, int pMaxNum) {
        node6D[] tree = new node6D[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new node6D();
            tree[i].num = i;
            tree[i].p = input[i].p;
        }
        node6D[] queue = new node6D[n];
        int front = 0;
        int rear = 0;
        queue[rear++] = input[pMaxNum];
        input[pMaxNum].isVisited = true;
        while (rear != front) {
            node6D tmp = queue[front++];
            for (int i = 0; i < tmp.children.size(); i++) {
                if (!tmp.children.get(i).isVisited) {
                    queue[rear++] = tmp.children.get(i);
                    tmp.children.get(i).isVisited = true;
                    tree[tmp.num].children.add(tree[tmp.children.get(i).num]);
                }
            }
        }
        return tree;
    }

    static int findSubMax(node6D node) {
        if (node.children.size() != 0) {
            int max = node.p;
            for (int i = 0; i < node.children.size(); i++) {
                max = Math.max(max, findSubMax(node.children.get(i)));
            }
            node.pMax_of_subtree = max;
            return max;
        } else {
            node.pMax_of_subtree = node.p;
            return node.p;
        }
    }

    static long calculateE(node6D[] tree, int pMaxNum, int n) {
        long ans = 0;
        for (int i = 0; i < tree[pMaxNum].children.size(); i++) {
            node6D tmpTop = tree[pMaxNum].children.get(i);
            node6D[] queue = new node6D[n];
            int front = 0;
            int rear = 0;
            queue[rear++] = tmpTop;
            while (rear != front) {
                node6D tmp = queue[front++];
                if (tmp.children.size() > 0) {
                    if (tmp.p >= tmp.pMax_of_subtree) {
                        // as the pMax_of_subtree is the max of p containing the node tmp,
                        // p can only < or == tmp.pMax_of_subtree
                        // NO!!!
                        // if the father of tmp is the largest,
                        // then maybe tmp will be resigned to the value of his father, causing >
                        int maxP = -1;
                        int index = -1;
                        for (int j = 0; j < tmp.children.size(); j++) {
                            queue[rear++] = tmp.children.get(j);
                            if (tmp.children.get(j).pMax_of_subtree > maxP) {
                                // not compare p but compare the pMax_of_subtree
                                maxP = tmp.children.get(j).pMax_of_subtree;
                                index = j;
                            }
                        }
                        tmp.children.get(index).p = tmp.p;
                    } else {
                        for (int j = 0; j < tmp.children.size(); j++) {
                            queue[rear++] = tmp.children.get(j);
                        }
                    }
                } else {
                    // to judge whether a node is a leave or not, NOT using its level,
                    // but you should be careful about its number of edges
                    ans += tmp.p;
                }
            }

        }
        return ans;
    }
}

class node6D {
    int num;
    int p;
    int pMax_of_subtree;

    ArrayList<node6D> children = new ArrayList<>();
    boolean isVisited;
}

