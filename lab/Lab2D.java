import java.io.*;
import java.util.StringTokenizer;

public class Lab2D {
    public static void main(String[] args) {
        // swap: if a > 0 and the highest bit is not the largest, then swap it first
        // like the SELECTION sort
        // if one operator cannot make ends meet, then stop
        // only store the profits, sort and do the highest
        // be careful about the steps
        // the cheaper step always done after expensive step
        // 2133 1233  you do not know you should swap which one to the highest bit

        // maybe there are a < 0
        // it is an exercises about HUNGER
        // so the best choice is to change the highest number to the highest index first
        // so first

        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, m, k;
        n = in.nextInt();
        m = in.nextInt();
        // at most m times
        k = in.nextInt();
        // cost of each swap
        int[] a = new int[n];
        int[] profit = new int[20000000];
        int profitIndex = 0;
        long sum_of_original_a = 0;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum_of_original_a += a[i];
            String str = String.valueOf(a[i]);
            int aiLength = str.length();
            int[] ai = new int[aiLength];

            // get every digit in the number a[i]
            for (int j = 0; j < aiLength; j++) {
                ai[j] = str.charAt(j) - '0';
                // small index stores the high digit of the number
            }

            if (a[i] > 0) {
                // make a[i] larger
                for (int j = 0; j < aiLength - 1; j++) {
                    int tmpMaxIndex = j;
                    for (int l = j + 1; l < aiLength; l++) {
                        // ????? l = j + 1    NOT i + 1 !!!
                        if (ai[l] > ai[tmpMaxIndex]) {
                            tmpMaxIndex = l;
                        }
                    }

                    if (tmpMaxIndex != j) {
                        int max = ai[tmpMaxIndex];
                        int min = ai[j];

                        profit[profitIndex] = (int) ((max - min) * Math.pow(10, aiLength - j - 1)
                                + (min - max) * Math.pow(10, aiLength - tmpMaxIndex - 1));
                        ++profitIndex;

                        ai[j] = max;
                        ai[tmpMaxIndex] = min;

                    }
                }
            } else {
                for (int j = 0; j < aiLength - 1; j++) {
                    int tmpMinIndex = j;
                    for (int l = j + 1; l < aiLength; l++) {
                        if (ai[l] < ai[tmpMinIndex]) {
                            tmpMinIndex = l;
                        }
                    }

                    if (tmpMinIndex != j) {
                        int min = ai[tmpMinIndex];
                        int max = ai[j];

                        profit[profitIndex] = (int) ((-1) * (min - max) * Math.pow(10, aiLength - j - 1)
                                + (-1) * (max - min) * Math.pow(10, aiLength - tmpMinIndex - 1));
                        ++profitIndex;

                        ai[j] = min;
                        ai[tmpMinIndex] = max;
                    }
                }
            }
        }
        int[] tmp = new int[20000000];
        mergeSort(profit, 0, profitIndex - 1, tmp);
        // the profit array should be sorted in descending order
        // because the high profit is needed
        int i = 0;
        while (profit[i] > k && i + 1 <= m) {
            sum_of_original_a += (profit[i] - k);
            ++i;
        }


        out.println(sum_of_original_a);
        out.close();
    }


    static void mergeSort(int[] a, int min, int max, int[] tmp) {
        int mid = min + (max - min) / 2;
        if (max > min) {
            mergeSort(a, min, mid, tmp);
            // mid NOT mid+1 ????
            mergeSort(a, mid + 1, max, tmp);
            merge(a, min, max, mid, tmp);
        }
    }

    static void merge(int[] a, int min, int max, int mid, int[] tmp) {
        int l = min;
        int r = mid + 1;
        int tmpIndex = min;
        while (l <= mid && r <= max) {
            if (a[l] >= a[r]) {
                tmp[tmpIndex] = a[l];
                ++l;
            } else {
                tmp[tmpIndex] = a[r];
                ++r;
            }
            ++tmpIndex;
        }

        while (l <= mid) {
            tmp[tmpIndex] = a[l];
            ++l;
            ++tmpIndex;
        }
        while (r <= max) {
            tmp[tmpIndex] = a[r];
            ++r;
            ++tmpIndex;
        }

        for (int i = min; i < max + 1; i++) {
            a[i] = tmp[i];
        }
    }
}





