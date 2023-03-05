import java.util.Scanner;

public class Lab1A {
    public static void main(String[] args) {
        int N, Q;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < Q; i++) {
            int x, y;
            x = sc.nextInt();
            y = sc.nextInt();
            // x < a < y
            test(a, N, x, y);
        }
    }

    static void test(int[] a, int N, int x, int y) {
        // binary search
        // use boundary index to make the interval more clear
        // not to forget any number
        if (x >= a[a.length - 1] || y <= a[0]) {
            System.out.println("NO");
            return;
        }

        int r = a.length - 1;
        int l = 0;
        while (l < r) {
            int mid = (l + r) / 2;
            if (a[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        int left = l;
        r = a.length - 1;
        l = 0;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (a[mid] >= y) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        int right = r;
        // think about what would happen if x > a[Q-1] or y < a[0] ?
        // when debugging, focus on these special occasions.
        if (right - left + 1 > 0) {
            System.out.printf("YES %d\n", right - left + 1);
        } else {
            System.out.println("NO");
        }
    }
}
