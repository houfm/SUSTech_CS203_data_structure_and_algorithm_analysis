import java.io.*;
import java.util.StringTokenizer;

public class Lab8D {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] a = new int[n + 1];
        long sum = 0;
        long[] prefixSum = new long[n + 1];
        // prefixSum[i] represent the sum of the first i numbers.
        boolean areConnected = true;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
            prefixSum[i] = sum;
            if (a[i] == 0) {
                areConnected = false;
            }
            if (a[i] < 0) {
                out.println("NO");
                out.println("NO");
                out.println("NO");
                out.close();
            }
        }
        // when n=1 ?
        if (sum % 2 == 0) {
            out.println("YES");
            if (isSimpleGraph(a, n,prefixSum)) {
                out.println("YES");
                if (sum == 2L * n - 2 && areConnected) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            } else {
                out.println("NO");
                out.println("NO");
            }
        } else {
            out.println("NO");
            out.println("NO");
            out.println("NO");
        }
        out.close();
    }

    public static boolean isSimpleGraph(int[] a, int n, long[] prefixSum) {
        int[] tmp = new int[n + 1];
        mergeSort(a, 1, n, tmp);
        long demand = 0;
        long supply = 0;
        long supply1 = 0;
        long supply2 = 0;
        long supply3 = 0;
        int k = n;
        // k represents the biggest index satisfying a[k]>=i.
        // ie. when index >= k+1, a[index] <= i;
        for (int i = 1; i <= n; i++) {
            supply1 = (long) i * (i - 1);
            demand += a[i];
            while (k >= 1 + i && k <= n && a[k] < i) {
                supply3 = (long) supply3 + a[k];
                // First put the current index into supply3,
                // THEN --k;
                --k;
            }
            if (k<=i){
                // Then k + 1 <= i + 1,
                // indicating thar every degree whose index greater than i is smaller than a[i].
                supply2=0;
            } else{
                supply2 = (long) (k-i)*i;
//                supply2 = prefixSum[k]-prefixSum[i];
                // index in range [i+1,k].
            }
            supply = supply1 + supply2 + supply3;
            if (supply  < demand) {
                return false;
            }
        }
        return true;
    }

    public static void mergeSort(int[] a, int min, int max, int[] tmp) {
        if (min < max) {
            int mid = (min + max) / 2;
            mergeSort(a, min, mid, tmp);
            mergeSort(a, mid + 1, max, tmp);
            merge(a, min, mid, max, tmp);
        }
    }

    static void merge(int[] a, int min, int mid, int max, int[] tmp) {
        int l = min;
        int r = mid + 1;
        int i = min;
        while (l <= mid && r <= max) {
            if (a[l] > a[r]) {
                tmp[i++] = a[l++];
            } else {
                tmp[i++] = a[r++];
            }
        }
        while (l <= mid) {
            tmp[i++] = a[l++];
        }
        while (r <= max) {
            tmp[i++] = a[r++];
        }
        for (int j = min; j < max + 1; j++) {
            a[j] = tmp[j];
        }
    }
}

