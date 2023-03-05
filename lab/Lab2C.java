import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Lab2C {
    // a[n/3]
    // lexicographical
    // only fix the order of the impossible medium
    // one number appears more than once, how to be lexicographical

    // read the description carefully, it asks for the medium NOT average

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        // do not forget to use fast read
        // do not forger to bring the QReader and QWriter class into this class
        int n = in.nextInt();
        // As n >= 3, the smallest number in this array always cannot be the medium
        // This does not depend on the number of n.
        // But for the second-smallest number or larger, it is quite different.
        // So what is needed to be considered is how to prevent these numbers to be the medium.
        int[] a = new int[n];
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        mergeSort(a, 0, n-1, tmp);

        int k = n/3;
        if (a[k - 1] == a[k]) {
            // As n >= 3, k >= 1, k is the index of the smallest possible medium
            // So the index of the smallest impossible medium is k - 1
            --k;
            // At this circumstance we only need to consider the largest number we can prevent from being the medium.
            // For example, a = {1,2,2,3,4,5,6,7,8}
            // k = 9/3 = 3
            // a[k] != a[k+1], but a[k-1] = a[k]
            // It is okay. because when k = 3, the number of small number a can protect is 3.
            // So although a[k-1] = a[k], a[k] can still be protected.
        }
        out.println(a[k]);

        for (int i = 0; i < k; i++) {
            tmp[i * 3] = a[i];
        }
        int j = 0;
        // index of the tmp
        for (int i = k; i < n; i++) {
            if (j % 3 == 0 && j <= k * 3 - 3) {
                ++j;
                // skip the element in array tmp
            }
            tmp[j] = a[i];
            ++j;
            // In this loop, i represents the index of a, we cannot skip any element in a
            // but for j, you should skip some indexes which have been placed smaller numbers.
        }
        for (int i = 0; i < n; i++) {
            out.print(tmp[i] + " ");
            // not println
            // There is a space between every numbers
            // print tmp NOT a
        }
        out.close();
    }

    static void mergeSort(int[] a, int min, int max, int[] tmp) {
        if (max > min){
            // ? forget to write while
            // ?????????????? not while but if
            // remember that the recursion must have an entrance
            int mid = min + (max - min) / 2;
            mergeSort(a, min, mid, tmp);
            mergeSort(a, mid + 1, max, tmp);
            merge(a, min, max, mid, tmp);
        }
    }

    static void merge(int[] a, int min, int max, int mid, int[] tmp) {
        int l = min;
        int r = mid + 1;
        int i = min;
        while (l <= mid && r <= max) {
            if (a[l] < a[r]) {
                // ? < not >
                tmp[i] = a[l];
                ++l;
            } else {
                tmp[i] = a[r];
                ++r;
            }
            ++i;
        }

        while (l <= mid) {
            tmp[i] = a[l];
            ++l;
            ++i;
        }
        while (r <= max) {
            tmp[i] = a[r];
            ++r;
            ++i;
        }

        for (int j = min; j < max + 1; j++) {
            a[j] = tmp[j];
        }
    }
}



