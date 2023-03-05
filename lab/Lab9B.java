import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Lab9B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        long gain = 0;
        node9B[] nodes = new node9B[n + 1];
        edge9B[] edges = new edge9B[m];
        for (int i = 0; i < n + 1; i++) {
            nodes[i] = new node9B();
        }
        PriorityQueue<edge9B> minHeap = new PriorityQueue(m, new edge9B_comparator());
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int weight = in.nextInt();
            edges[i] = new edge9B(weight, nodes[x], nodes[y]);
            nodes[x].edges.add(edges[i]);
            nodes[y].edges.add(edges[i]);
        }
        nodes[1].isVisited = true;
        for (int i = 0; i < nodes[1].edges.size(); i++) {
            minHeap.add(nodes[1].edges.get(i));
        }
        node9B cur;
        first: while (!minHeap.isEmpty()) {
            edge9B current_min = minHeap.poll();
            while (current_min.vertex1.isVisited && current_min.vertex2.isVisited){
                if (minHeap.size()>0) {
                    // Although we let the condition of the first while to be !empty,
                    // the heap may become empty when delete useless edges.
                    current_min = minHeap.poll();
                } else {
                    break first;
                }

            }
            current_min.is_in_spanning_tree = true;
            if (current_min.vertex1.isVisited && !current_min.vertex2.isVisited) {
                cur = current_min.vertex2;
            } else {
                cur = current_min.vertex1;
            }
            cur.isVisited=true;
            for (int i = 0; i < cur.edges.size(); i++) {
                if (!(cur.edges.get(i).vertex1.isVisited && cur.edges.get(i).vertex2.isVisited)) {
                    minHeap.add(cur.edges.get(i));
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (!edges[i].is_in_spanning_tree && edges[i].weight>0) {
                gain = gain + (long) edges[i].weight;
            }
        }
        out.print(gain);
        out.close();
    }
}

class node9B {
    boolean isVisited;
    ArrayList<edge9B> edges = new ArrayList<>();
}

class edge9B_comparator implements Comparator<edge9B> {

    @Override
    public int compare(edge9B o1, edge9B o2) {
        if (o1.weight < o2.weight) {
            return -1;
        } else if (o1.weight > o2.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}

class edge9B {
    boolean is_in_spanning_tree;
    int weight;
    node9B vertex1;
    node9B vertex2;

    public edge9B(int weight, node9B vertex1, node9B vertex2) {
        this.weight = weight;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    //    public edge9B(int weight) {
//        this.weight = weight;
//
//    }
}
