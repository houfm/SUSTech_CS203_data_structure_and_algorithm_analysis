import java.io.*;
import java.util.StringTokenizer;

public class Lab5C {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        String str = in.nextLine();
        int length = str.length();
        int[][] ans = new int[length][26];
        int[] intAt = new int[length];
        // a = 97
        for (int i = 0; i < length; i++) {
            intAt[i] = str.charAt(i) - 'a';
            ans[i][intAt[i]] = i+1;
        }
        int next=0;
        for (int i = 1; i < length; i++) {
            // 0 is the state that nothing has been input
            if (i >1){
                next = ans[next][str.charAt(i-1) - 'a'];
                // there is no need to use next method
                // i-1 not i
            }
            for (int j = 0; j < 26; j++) {
                if (j != intAt[i]) {
                    ans[i][j] = ans[next][j];
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 26; j++) {
                if (j == 25){
                    out.println(ans[i][j]);
                } else {
                    out.print(ans[i][j] + " ");
                    // not ' '
                }
            }
        }
        out.close();
    }

}



