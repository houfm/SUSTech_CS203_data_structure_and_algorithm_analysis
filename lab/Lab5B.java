import java.io.*;
import java.util.StringTokenizer;
public class Lab5B {
    public static void main(String[] args) {
        // 31
        // modulo & flag array
        QWriter out = new QWriter();
        QReader in = new QReader();
        String a, b;
        a = in.nextLine();
        b = in.nextLine();
        int aL = a.length();
        int bL = b.length();
        int length = Math.min(aL, bL);
        int ans = 0;
        int n = length / 2;
        if (length % 2 == 0) {
            // length is even
            // odd and even number is all n pieces
            ans = oddBinarySearch(0, n, a, b);
            // l=0 not 1
            // if l=1, then the ans will always >=1, the print -1 is impossible
            // the left boundary should be 1
            // as the things that is divided is the length rather than index
        } else {
            ans = oddBinarySearch(0, n + 1, a, b);
        }
        // before next statement, ans is equal to HALF of the longest length of palindrome
        ans = Math.max(2 * ans - 1, 2 * evenBinarySearch(0, n, a, b));

        if (ans < 1) {
            // not <=
            out.println(-1);
        } else {
            out.println(ans);
        }
        out.close();
    }

    static int oddBinarySearch(int l, int r, String a, String b) {
        while (l < r) {
            int mid = l + (r - l) / 2 + 1;
            // l+(r-l)/2 == (l+r)/2
            if (common(a, b, 2 * mid - 1)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    // If the 2 String have palindrome of length n,
    // we can only know that the 2 String will have palindrome of length n-2
    // rather than n-1
    // so there should be 2 binary search
    static int evenBinarySearch(int l, int r, String a, String b) {
        while (l < r) {
            int mid = l + (r - l) / 2 + 1;
            // l+(r-l)/2 == (l+r)/2
            if (common(a, b, 2 * mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    static boolean common(String a, String b, int subLength) {
        int[] ansA = palindrome(subLength, a);
        int[] ansB = palindrome(subLength, b);
        int ansALen = ansA[0];
        int ansBLen = ansB[0];
        // ?????
        int[] tmp = new int[ansALen + 1];
        mergeSort(1,ansALen,ansA,tmp);
        for (int i = 1; i <= ansBLen; i++) {
            int min = 1;
            int max = ansALen;
            int target = ansB[i];
            while(min < max) {
                int mid = min + (max - min) / 2;
                if (ansA[mid] > target){
                    max = mid-1;
                } else if (ansA[mid] < target) {
                    min = mid+1;
                } else {
                    return true;
                    // this condition is ignored.
                }
            }
            if (ansA[min]==target){
                return true;
            }
        }
        return false;
    }

    static int[] palindrome(int subLength, String str) {
        int num = 0;
        int length = str.length();
        int[] ans = new int[length - subLength + 2];
        // find the palindrome which length is mid
        // declare vs initialize
        // arrays in Java have fixed length
        // not +1 not +0 but +2
        int[] o = subString(subLength, str);
        // original
        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        String strT = sb.toString();
        int[] t = subString(subLength, strT);
        for (int i = 0; i < length - subLength + 1; i++) {
            if (o[i] == t[length - subLength - i]) {
                // not o[i]==t[i]
                num++;
                ans[num] = o[i];
            }
        }
        ans[0] = num;
        return ans;
    }

    static int[] subString(int subLength, String str) {
        int base = 31;
        int length = str.length();
        int[] ans = new int[length - subLength + 1];
        int[] pow = new int[subLength];
        pow[0] = 1;
        for (int i = 1; i < subLength; i++) {
            pow[i] = pow[i - 1] * base;
        }
        for (int i = 0; i < subLength; i++) {
            ans[0] += (str.charAt(i) - 'a') * pow[subLength - 1 - i];
            // the return type of Math.pow is double
            // pow overflow
        }
        for (int i = 1; i < length - subLength + 1; i++) {
            ans[i] = (ans[i - 1] - (str.charAt(i - 1) - 'a') * pow[subLength - 1]) * base
                    + str.charAt(i + subLength - 1) - 'a';
        }
        return ans;
    }

    static void mergeSort(int min, int max, int[] a, int[] tmp) {
        if (min < max) {
            int mid = min + (max - min) / 2;
            mergeSort(min, mid, a, tmp);
            mergeSort(mid + 1, max, a, tmp);
            merge(min, mid, max,a, tmp);
        }
    }

    static void merge(int min, int mid, int max, int[] a, int[] tmp) {
        int l = min;
        int r = mid + 1;
        int i = min;
        while (l <= mid && r <= max) {
            if (a[l] <= a[r]) {
                tmp[i++] = a[l++];
            } else {
                tmp[i++] = a[r++];
            }
        }

        while (l <=mid){
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


