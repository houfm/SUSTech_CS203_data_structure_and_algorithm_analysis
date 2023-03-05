public class Lab7A {
    public static void main(String[] args) {
        int n, m, k;
        QWriter out = new QWriter();
        QReader in = new QReader();
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[] a = new int[n];
        int[] tmp = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        mergesort(a,tmp,0,n-1);

        heap7A heap = new heap7A();
        for (int i = 0; i < m; i++) {
            long c = (long) a[0]*b[i];
            node7A node = new node7A(0,i,c);
            heap.insert(node);
        }

        for (int i = 0; i < k; i++) {
            if (i!=0){
                out.print(" ");
            }
            out.print(heap.heap[1].c);
            int minAIndex= heap.heap[1].aIndex;
            int minBIndex= heap.heap[1].bIndex;
            heap.remove();
            if (minAIndex+1<n){
                long c = (long) a[minAIndex+1]*b[minBIndex];
                node7A node = new node7A(minAIndex+1,minBIndex,c);
                heap.insert(node);
            }

        }
        out.close();
    }

    static void mergesort(int[] a, int[] tmp, int min, int max) {
        if (min < max) {
            int mid = min + (max - min) / 2;
            mergesort(a, tmp, min, mid);
            mergesort(a, tmp, mid + 1, max);
            merge(a, tmp, min, mid, max);
        }
    }

    static void merge(int[] a, int[] tmp, int min, int mid, int max) {
        int cur1 = min;
        int cur2 = mid+1;
        int curTmp = min;
        while (cur1 <= mid && cur2 <= max) {
            if (a[cur1]< a[cur2]) {
                tmp[curTmp++] = a[cur1++];
            } else{
                tmp[curTmp++] = a[cur2++];
            }
        }
        while (cur1 <= mid) {
            tmp[curTmp++] = a[cur1++];
        }
        while (cur2 <= max) {
            tmp[curTmp++] = a[cur2++];
        }
        for (int i = min; i <= max; i++) {
            a[i] =tmp[i];
        }
    }
}

class node7A {
    int aIndex;
    int bIndex;
    // aIndex and bIndex are the index of element in array a and b which make the value c
    long c;
    // c = (long)a * b.
    // The 'long' in the parenthesis make sure that
    // the result of a*b will be correct even if it exceeds the range of int.

    public node7A(int aIndex, int bIndex, long c) {
        this.aIndex = aIndex;
        this.bIndex = bIndex;
        this.c = c;
    }
}

class heap7A {
    // This is a MIN root heap.
    node7A[] heap = new node7A[1000000];
    int size = 0;
    // As the key of the top node is 1,
    // size++ is executed before we insert a new node


    public void insert(node7A a) {
        ++size;
        heap[size] = a;
        int cur = size;
        // cur indicate the current index
        while (cur > 1 && heap[cur].c < heap[cur / 2].c) {
            node7A tmp = heap[cur];
            heap[cur] = heap[cur / 2];
            heap[cur / 2] = tmp;
            cur = cur / 2;
        }
    }

    public void remove() {
        // The method is only used for remove the top of the heap.
        // So if you want to take out the top element,
        // you should get it then call the method.
        heap[1] = heap[size];
        --size;
        int cur = 1;
        int minChildIndex =2;
        // Let the default max child always be the first(left) child;
        while (minChildIndex <=size){
            if (minChildIndex +1 <= size &&heap[minChildIndex].c > heap[minChildIndex +1].c) {
                minChildIndex = minChildIndex + 1;
            }
            if (heap[minChildIndex].c < heap[cur].c){
                node7A tmp = heap[cur];
                heap[cur] = heap[minChildIndex];
                heap[minChildIndex] = tmp;
                cur = minChildIndex;
                minChildIndex = 2*cur;
            } else {
                break;
            }
        }

    }
}


