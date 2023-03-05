import java.io.*;
import java.util.StringTokenizer;

public class Lab4D {
    // sequence, addable,minus: prefix sum
    // put the first sum as 0, otherwise the first element cannot be used
    // sort: index & prefix sum
    // sort after getting prefix sum
    // not know why to sort, but sort to see what will happend
    // find prefix b < prefix a( make the sum is >0), and index of b and a have the most difference
    // find the min index on the left of cursor
    // two prefix sum are same?
    // reserve two minimum
    // 2on

    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int n = in.nextInt();
        int[][] sum = new int[2][n + 1];
        // sum[0] store the prefix sum
        // sum[1] represents the number of elements added in the prefix sum
        int[][] tmp = new int[2][n + 1];
        sum[0][0] = 0;
        sum[1][0] = 0;
        // the index of sum is the number of elements in prefix sum;
        for (int i = 0; i < n; i++) {
            sum[0][i + 1] = sum[0][i] + in.nextInt();
            sum[1][i + 1] = i + 1;
        }
        mergeSort(sum, 0, n, tmp);
        // the mergesort_cost should start from 0 rather than 1 as the smallest number may be smaller than 0
        int[] minIndex = new int[n + 10];
        // the minIndex ranging from 0 to i
        int maxLength;
        int realMinIndex;
        int nowLength;

        minIndex[0] = sum[1][0];
        if (sum[0][0] > 0) {
            maxLength = 1;
        } else {
            maxLength = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            // just start from i=1 would be okay
            if (sum[0][i] != sum[0][i - 1]) {
                nowLength = sum[1][i] - minIndex[i - 1];
            } else {
                int difIndex = i - 1;
                while (difIndex >= 1 && sum[0][i] == sum[0][difIndex]) {
                    // index out of bounds
                    // and difIndex>=1 NOT >=0 as if it gets into the loop, it will minus 1
                    difIndex--;
                }
                nowLength = sum[1][i] - minIndex[difIndex];
            }
            maxLength = Math.max(maxLength, nowLength);
            minIndex[i] = Math.min(sum[1][i], minIndex[i - 1]);
            // use min method to replace an if
        }

        out.println(maxLength);
        out.close();
    }

    static void mergeSort(int[][] a, int min, int max, int[][] tmp) {
        if (min < max) {
            int mid = min + (max - min) / 2;
            // ？ / not +
            mergeSort(a, min, mid, tmp);
            mergeSort(a, mid + 1, max, tmp);
            merge(a, min, mid, max, tmp);
            // first think to add the method after creating the method, but forget to do so
        }
    }

    static void merge(int[][] a, int min, int mid, int max, int[][] tmp) {
        int l = min;
        int r = mid + 1;
        int i = min;
        while (l <= mid && r <= max) {
            // ???? l<=mid NOT min
            if (a[0][l] < a[0][r]) {
                tmp[0][i] = a[0][l];
                tmp[1][i++] = a[1][l++];
                // x = a[i++]: after letting x = a[i], i++
                // if i++ when assign both a[0] and a[1], then i increased 2 in one row
                // and the ++ should be placed on the second statement
            } else {
                tmp[0][i] = a[0][r];
                tmp[1][i++] = a[1][r++];
            }
        }

        while (l <= mid) {
            tmp[0][i] = a[0][l];
            tmp[1][i++] = a[1][l++];
        }
        while (r <= max) {
            tmp[0][i] = a[0][r];
            tmp[1][i++] = a[1][r++];
        }

        for (int j = min; j < max + 1; j++) {
            a[0][j] = tmp[0][j];
            a[1][j] = tmp[1][j];
        }
    }
}


