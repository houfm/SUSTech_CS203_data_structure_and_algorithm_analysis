import java.io.*;
import java.util.StringTokenizer;

public class Lab4A {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        String str = in.nextLine();
        int n = str.length();
        char c;
        stack s = new stack();
        for (int i = 0; i < n; i++) {
            c = str.charAt(i);
            switch (c) {
                case '(':
                    s.addNew();
                    break;
                case ')':
                    s.merge();
                    break;
            }
        }
        out.print(s.sum);
        out.close();
    }
}

class stack {
    int sum = 0;
    // forget to consider inputs like: (())(), ()()
    int[] a = new int[200000];
    int top = 0;
    int mode = 514329;

    // top is still not be assigned;
    void addNew() {
        a[top] = 0;
        ++top;
    }

    void merge() {
        top--;
        if (a[top] == 0) {
            if (top >= 1) {
                a[top - 1] = (a[top - 1] + 1) % mode;
                // not =
            } else {
                sum = (sum + a[top] + 1) % mode;
                // mode not only when *2, but when +1
            }

        } else {
            if (top >= 1) {
                a[top - 1] = (a[top - 1] + a[top] * 2) % mode;
                // += NOT =
                // = + NOT +=
                // where to use mode
            } else {
                sum = (sum + a[top] * 2) % mode;
            }
        }
    }
}

