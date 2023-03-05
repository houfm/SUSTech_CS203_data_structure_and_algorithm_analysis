public class Lab7B {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            heap7B heap = new heap7B(n);
            for (int j = 0; j < n; j++) {
                heap.insert(in.nextInt());
            }
            long ans = 0;
            int a, b, c;
            for (int j = 0; j < n - 1; j++) {
                a = heap.heap[1];
                heap.delete();
                b = heap.heap[1];
                heap.delete();
                c = a + b;
                ans += c;
                heap.insert(c);
            }
            out.println(ans);
        }
        out.close();
        // do not forget it.
        // otherwise you won't get any output
    }
}

class heap7B {
    int[] heap;
    int size = 0;

    public heap7B(int n) {
        heap = new int[n + 1];
    }

    public void insert(int x) {
        size++;
        heap[size] = x;
        int temp = size;
        while (temp > 1 && heap[temp / 2] > heap[temp]) {
            // not the top && father is smaller
            int t = heap[temp / 2];
            heap[temp / 2] = heap[temp];
            heap[temp] = t;
            temp = temp / 2;
        }
    }

    public void delete() {
        heap[1] = heap[size];
        size--;
        int cur = 1;
        int min_child = cur * 2;
        while (min_child <= size) {
            if (min_child + 1 <= size && heap[min_child] > heap[min_child + 1]) {
                ++min_child;
            }
            if (heap[cur] <= heap[min_child]) {
                break;
            } else {
                int temp = heap[cur];
                heap[cur] = heap[min_child];
                heap[min_child] = temp;
                cur = min_child;
                min_child = cur * 2;
            }
        }
    }
}

