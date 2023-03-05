import java.io.*;
import java.util.StringTokenizer;

public class Lab4C {
    // 滑动窗口
    // 如果不进行预处理，时间复杂度会是某数与q相乘
    // 单调队列：队列中的元素有单调性：扔掉不是极值的：所以只能计算最大最小
    // 如何维护单调性：要递减，2进来之前把前面比他小的都扔掉
    // k is a constant
    // 模拟类型
    // head tail
    // 循环数组：取模，加一
    // 进队的时候有值大小和位置
    // 进队越早，下标越小
    // 先问前面的是否在区间内
    // 复杂度：on

    // we use monotone queue to store the maximum of an interval
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int n, k, q;
        n = in.nextInt();
        k = in.nextInt();
        // k is the length
        q = in.nextInt();
        // q times of query
        queue4c queue = new queue4c();
        int[] a = new int[n];
        int[] ans = new int[n];
        // the index of the
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (a[i] != queue.de[queue.head]) {
                queue.enQueue(a[i], i);
            }
            if (i >= k-1) {
                ans[i-k+1] = queue.check(i-k+1, i);
                // although we need the index of array ans match the index of the first element in the interval,
                // compute the ans AFTER we have added all the elements into queue
            }
        }
        int x;
        for (int i = 0; i < q; i++) {
            x = in.nextInt() - 1;
            // x is the starting index
            out.println(ans[x]);
        }
        out.close();
    }
}

class queue4c {
    int[] de = new int[4000000];
    int[] index = new int[4000000];
    int head = 0;
    // the first index of element NOT in the queue
    int tail = 0;

    void enQueue(int a, int aIndex) {
        if (head != 0) {
            if (a < de[head - 1]) {
                // -1: index may out of bounds
                this.de[head] = a;
                this.index[head] = aIndex;
                head++;
            } else if (a > de[head - 1]) {
                while (head - 1 >= 0 && a > de[head - 1]) {
                    // index out of bounds
                    // until head = 0 or a < de
                    head--;
                }
                de[head] = a;
                index[head] = aIndex;
                head++;
            } else {
                index[head - 1] = aIndex;
            }
        } else {
            this.de[head] = a;
            this.index[head] = aIndex;
            head++;
        }
    }

    int check(int l, int r) {
        for (int i = tail; i < head; i++) {
            if (index[i] >= l && index[i] <= r) {
                return de[i];
                // ????? NOT index
            }
        }
        return 0;
    }
}


