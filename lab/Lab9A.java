import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
9 13
3 2 1
2 1 1
1 4 2
1 5 10
4 1 6
4 7 1
5 4 5
6 5 1
7 6 2
7 9 1
8 4 3
8 7 1
9 6 2
*/

public class Lab9A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node9A[] node = new node9A[n + 1];
        heap9A heap = new heap9A(n);
        for (int i = 0; i < n + 1; i++) {
            node[i] = new node9A();
            node[i].distance = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int weight = in.nextInt();
            node[x].add_road(y, weight);
            // unidirectional road
        }
        heap.insert(node[1]);
        node[1].distance = 0;
        node9A tmp;
        node9A tmp_child;
        while (heap.size > 0) {
            tmp = heap.node[1];
            tmp.isVisited = true;
            heap.remove();
            for (int i = 0; i < tmp.children.size(); i++) {
                tmp_child = node[tmp.children.get(i).child_index];
                if (!tmp_child.isVisited) {
                    long new_distance = tmp.distance + (long) tmp.children.get(i).weight;
                    // be careful about the cases at this place.
                    // for every child, it can be either in the heap or not.
                    // If it is in the heap, it will be done operation only
                    // when the new distance is smaller than the old distance.
                    // But NOT in all other circumstances will it be inserted,
                    // because the child may be in the heap, but the new distance is larger than the old distance.
                    if (tmp_child.index_in_heap != 0 ) {
                        if (new_distance < tmp_child.distance){
                            heap.revise(tmp_child.index_in_heap, new_distance);
                        }
                    } else{
                        tmp_child.distance = new_distance;
                        heap.insert(tmp_child);
                    }
                }
            }
//            if (tmp.equals(node[n])) {
//                break;
//            }
        }
        if (node[n].distance == Long.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(node[n].distance);
        }
        out.close();
    }
}

class heap9A {
    int size = 0;
    node9A[] node;

    public heap9A(int n) {
        node = new node9A[n];
    }

    public void insert(node9A node) {
        this.node[++size] = node;
        node.index_in_heap = size;
        int cur = size;
        while (cur > 1 && this.node[cur / 2].distance > this.node[cur].distance) {
            exchange_node(cur, cur / 2);
            cur = cur / 2;
        }
    }

    public void remove() {
        node[1] = node[size];
        node[1].index_in_heap = 1;
        size--;
        int cur = 1;
        while (cur * 2 <= size) {
            int min_index = cur * 2;
            if (cur * 2 + 1 <= size && node[cur * 2 + 1].distance < node[min_index].distance) {
                min_index = cur * 2 + 1;
            }
            if (node[min_index].distance < node[cur].distance) {
                exchange_node(cur, min_index);
                cur = min_index;
            } else {
                break;
            }
        }
    }

    public void revise(int index_in_heap, long new_distance) {
        // As the new distance will be smaller than the previous distance,
        // the index of the node in the heap will be SMALLER.
        node[index_in_heap].distance = new_distance;
        int cur = index_in_heap;
        while (cur > 1 && this.node[cur / 2].distance > this.node[cur].distance) {
            exchange_node(cur, cur / 2);
            cur = cur / 2;
        }
    }

    public void exchange_node(int i, int j) {
        node9A tmp = node[i];
        node[i] = node[j];
        node[j] = tmp;
        node[j].index_in_heap = j;
        node[i].index_in_heap = i;
    }
}

class node9A {
    boolean isVisited;
    long distance;
    int index_in_heap;
    // when not in heap, let it be 0. (the default value)
    ArrayList<road9A> children = new ArrayList<>();

    public void add_road(int child_index, int weight) {
        children.add(new road9A(child_index, weight));
    }
}

class road9A {
    int child_index;
    int weight;

    public road9A(int child_index, int weight) {
        this.child_index = child_index;
        this.weight = weight;
    }
}

