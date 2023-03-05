import java.util.Scanner;

public class Lab0CNew {
    public static void main(String[] args) {
        int x1, y1, x2, y2, M;
        Scanner sc = new Scanner(System.in);
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();
        M = sc.nextInt();
        // default value of long type is 0L, NOT 0;
        // an integer literal is long if it is ended with L or l.
        int x = x2 - x1;
        int y = y2 - y1;
        // automatic and explicit type conversion/casting:
        // automatic: target type is larger
        long[][] ans = new long[1001][1001];
        // how to define a two-dimensional array
        // ? long[][] ans = ans[1001][1001];
        // ? long[][] ans = new ans[1001][1001];
        for (int i = 0; i < x+1; i++) {
            ans[i][0]=1;
        }
        for (int i = 0; i < y + 1; i++) {
            ans[0][i]=1;
        }
        for (int i = 1; i < x+1; i++) {
            for (int j = 1; j < y + 1; j++) {
                ans[i][j]=ans[i-1][j]+ans[i][j-1];
                ans[i][j]%=M;
                // it is safe to convert a
            }
        }
        System.out.print(ans[x][y]);
    }

}
