import java.util.Scanner;

public class Lab2A {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int[] tmp = new int[n];
        sort(a, tmp, 0, n-1 );

        long sum = 0L;

        for (int i = 0; i < n/2; i++) {
            sum += (long) a[i] *a[n-1-i];
        }

        System.out.println(sum);
    }

    static void sort(int[] a, int[] tmp, int min, int max) {
        if (max > min) {
            int mid = (max + min) / 2;
            sort(a, tmp, min, mid);
            sort(a, tmp, mid + 1, max);
            merge(a, tmp, min, max, mid);
        }
    }

    static void merge(int[] a, int[] tmp, int min, int max, int mid) {
        // as we have guaranteed that the two small arrays (min to mid and mid and max) have been sorted
        // now we want to put the 2 sorted small arrays into a larger sorted one
        int i = 0;
        int j = min, k = mid + 1;
        while (j <= mid && k <= max) {
            if (a[j] > a[k]) {
                tmp[i++] = a[j++];
            } else {
                tmp[i++] = a[k++];
            }
        }

        while (j <= mid){
            tmp[i++] = a[j++];
        }
        while (k <= max){
            tmp[i++] = a[k++];
            // ? j
        }
        //??

        for (int l = 0; l < i; l++) {
            a[min+l] = tmp[l];
        }

    }
}
