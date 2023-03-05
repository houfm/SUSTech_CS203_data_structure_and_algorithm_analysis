import java.util.Scanner;

public class Lab1B {
    public static void main(String[] args) {
        int n, s;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        triple(a, s);
    }

    static void triple(int[] a, int s) {
        int n = a.length;
        long cnt = 0;
        // !!! be careful about the required size of variable
        // before start to define a variable, you should consider which data type I should use
        for (int i = 0; i < n - 2; i++) {
            if (a[i] + a[i+1] + a[i+2] > s) {
                break;
            } else {
                // how to use binary search in this problem?
                // If you use binary search to find the third number, it is hard to satisfy i<j<k;
                // So in this problem, we use binary search to find the second and the third number at once.
                // as r decrease, a[r] decrease; as l increase, a[l] increase.
                // so a[r] + a[l] can equal to key in different situations in the loop.
                int key = s - a[i];
                int r = n - 1;
                int l = i + 1;
                // do not be confused by r and l
                while (r > l) {
                    if (a[l] + a[r] == key) {
                        if (a[l] == a[r]) {
                            // when a[l] equals a[r], this case must be separated from other case
                            // because at thin time combination must be used.
                            cnt += ((r - l + 1) * (r - l) / 2);
                            // ? r-l NOT l-r
                            // it is better to use max and min to replace right and left
                            break;
                        } else {
                            int cntl = 1, cntr = 1;
                            while (a[r] == a[r - 1]) {
                                ++cntr;
                                --r;
                            }
                            while (l + 1 < n && a[l] == a[l + 1]) {
                                // l + 1 may cause index out of bound
                                ++cntl;
                                ++l;
                            }
                            --r;
                            ++l;
                            cnt += cntr * cntl;
                        }
                    } else if (a[l] + a[r] < key) {
                        ++l;
                    } else {
                        --r;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
