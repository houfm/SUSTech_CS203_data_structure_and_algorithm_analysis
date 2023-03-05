import java.io.*;
import java.util.StringTokenizer;

public class Lab3C {
    // linked list
    // first sort: then the answer only comes from neighborhood
    // delete the numbers whose index is smaller than now
    // the linked list should be two-side
    // make it a linked list after sort
    // try to design an o(1) sort
    // use a node array to store the node with certain id
    // do it while linking the linked array
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[][] a = new int[2][n];
        for (int i = 0; i < n; i++) {
            a[0][i] = in.nextInt();
            a[1][i] = i;
        }
        int[][] tmp = new int[2][n];
        merge(a,0,n-1,tmp);

        node3c[] curIndex = new node3c[n];
        // cursor
        node3c cur;
        node3c[] nodeA = new node3c[n] ;

        nodeA[0] = new node3c(a[0][0]);
        cur = nodeA[0];
        curIndex[a[1][0]] = nodeA[0];
        // forget to assign the nodeA to the index 0
        // maybe use a method is better
        // if you use a fake head and connect it to the real head,
        // it will bring troubles when calculating the min difference
        for (int i = 1; i < n; i++) {
            nodeA[i] = new node3c(a[0][i]);
            cur.next = nodeA[i];
            nodeA[i].pre = cur;
            cur = nodeA[i];

            curIndex[a[1][i]] = nodeA[i];
            // remember curIndex itself does not matter
            // what we need is its next
            // no!!!!
            // see how ta use cur
            // cur is just usd as a cursor
            // just assign it to the node rather than let its next be assigned to the node
        }

        int dif1=0,dif2=0;
        for (int i = 0; i < n-1; i++) {
            // for n elements, there are only n-1 output numbers
            if (i>0){
                remove(curIndex[i-1]);
            }
            cur = curIndex[i];
            if (cur.pre != null){
                dif1 = cur.a - cur.pre.a;
            }
            if (cur.next != null){
                // cur.next maybe null
                dif2 = cur.next.a - cur.a;
            }

            if (cur.pre != null && cur.next != null){
                out.println(Math.min(dif1,dif2));
            } else if (cur.pre != null) {
                out.println(dif1);
            } else {
                out.println(dif2);
            }
        }
        out.close();
    }

    static void remove(node3c a){
        if (a.pre != null){
            a.pre.next = a.next;
            // a.pre can be null
        }
        if (a.next != null){
            a.next.pre = a.pre;
        }
    }

    static void merge(int[][] a, int min, int max,int[][] tmp) {
        // max,min NOT first,next
        if (max > min) {
            int mid = min + (max - min) / 2;
            // when max = min + 1
            // only the "mergeSort(a,min,mid,max,tmp);" statement is available
            merge(a, min, mid, tmp);
            merge(a, mid + 1, max, tmp);
            mergeSort(a,min,mid,max,tmp);
        }
    }
    static void mergeSort(int[][] a,int min, int mid, int max, int[][] tmp){
        int l = min;
        int r = mid+1;
        // NOT mid, but mid+1
        // r=mid+1 NOT max
        // just write comments but does not change the code
        // ...........
        int i = min;
        while (l <=mid && r <= max ){
            if (a[0][l] < a[0][r]){
                // ??? not >
                tmp[0][i] = a[0][l];
                tmp[1][i] = a[1][l];
                ++l;
            } else {
                tmp[0][i] = a[0][r];
                tmp[1][i] = a[1][r];
                ++r;
            }
            ++i;
        }

        while (l <= mid){
            tmp[0][i] = a[0][l];
            tmp[1][i] = a[1][l];

            ++l;
            ++i;
        }
        while (r <= max){
            tmp[0][i] = a[0][r];
            tmp[1][i] = a[1][r];

            ++r;
            ++i;
        }

        for (int j = min; j < max+1; j++) {
            a[0][j] = tmp[0][j];
            a[1][j] = tmp[1][j];

        }
    }
}

class node3c{
    int a;
    node3c next;
    node3c pre;
    public node3c(int a) {
        this.a = a;
    }
}


