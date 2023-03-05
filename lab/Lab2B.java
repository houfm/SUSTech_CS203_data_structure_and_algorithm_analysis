import java.io.*;
import java.util.StringTokenizer;

public class Lab2B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n;
        n = in.nextInt();
        // number of test case
        int[][] a = new int[n][100000];
        int[] tmp = new int[100000];
        for (int i = 0; i < n; i++) {
            int length = in.nextInt();
            for (int j = 0; j < length; j++) {
                a[i][j] = in.nextInt();
            }

            long cnt = 0;
            // int is not enough
            cnt = mergeSort(a[i], 0, length - 1, tmp);
            // ?? max = length - 1 not n - 1
            // remember to name the variable using the same name in the description
            // remember Java is always pass-by-value.(value of reference)
            // there are 3 circumstances: primitive data type, String, Objects
            // if you just call a method whose return type is not void but does not let it be assigned to a variable
            // its return value will be useless
            out.println(cnt);
        }
        out.close();
    }

    static long mergeSort(int[] a, int min, int max, int[] tmp) {
        long cnt = 0;
        if (max > min) {
            int mid = min + (max - min) / 2;
            cnt += mergeSort(a, min, mid, tmp);
            cnt += mergeSort(a, mid + 1, max, tmp);
            cnt += merge(a, min, mid, max, tmp);
        }
        return cnt;
    }

    static long merge(int[] a, int min, int mid, int max, int[] tmp) {
        long cnt = 0;
        int l = min;
        int r = mid + 1;
        int i = min;
        // int[] tmp = new int[a.length];
        // it will waste too much time
        while (l <= mid && r <= max) {
            if (a[l] <= a[r]) {
                tmp[i] = a[l];
                ++i;
                ++l;
            } else {
                tmp[i] = a[r];
                ++i;
                ++r;
                cnt += mid - l + 1;
                // bubble sort works on swapping ADJACENT elements
                // but in merger sort, when inserting an element into the former array, it may move more than one index
                // So it is improper to use ++cnt at this place
            }
        }

        while (l <= mid) {
            // ? not < but <=
            // when l == mid, it is still needed to put l into tmp
            tmp[i] = a[l];
            ++i;
            ++l;
            // if at this place still do ++cnt;
            // think about how to find inversion numbers?
            // when a number from the second array is inserted into the first array in the first while loop
            // it is equal to move the left numbers in the first array to the end of the new array
            // So it is unnecessary to ++cnt at this while loop
        }
        while (r <= max) {
            tmp[i] = a[r];
            ++i;
            ++r;
        }

        for (int j = min; j < max + 1; j++) {
            a[j] = tmp[j];
            // ?????? a[i] = tmp[i];
            // be careful! This is j NOT i!!!!!!
        }
        // do not forget to replace a with tmp

        return cnt;
    }

    // in mac, use command + / to annotate
//    static void bubbleSort(int length, int[] a) {
//        int cnt = 0;
//        for (int j = 0; j < length - 1; j++) {
//            for (int k = j; k < length - 1; k++) {
//                // when you need to use the index used in a loop, especially when there are more than 1 loop,
//                // be careful that the name of the variable is NOT i
//                if (a[k] > a[k + 1]) {
//                    int tmp = a[k];
//                    a[k] = a[k + 1];
//                    a[k + 1] = tmp;
//                    ++cnt;
//                }
//            }
//        }
//        System.out.println(cnt);
//    }
}


