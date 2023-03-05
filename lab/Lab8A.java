import java.io.*;
import java.util.StringTokenizer;

public class Lab8A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n,m,q;
        n = in.nextInt();
        m = in.nextInt();
        q = in.nextInt();
        int[][] a = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            a[tmp1][tmp2] = 1;
            a[tmp2][tmp1] = 1;
        }
        for (int i = 0; i < q; i++) {
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            if (a[tmp1][tmp2]==1){
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
        out.close();
    }
}


