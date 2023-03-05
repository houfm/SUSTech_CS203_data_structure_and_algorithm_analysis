import java.io.*;
import java.util.StringTokenizer;

public class Lab5A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        first:
        for (int i = 0; i < T; i++) {
            String t = in.nextLine();

            int n = in.nextInt();
            // the number of string to color the text
            String[] s = new String[n];
            for (int j = 0; j < n; j++) {
                s[j] = in.nextLine();
            }

            int length = t.length();
            // the length of the text
            int[][] rMax = new int[length][2];
            // the max length of the string that can be used to color the text
            // 0: index of array s
            // 1 : rMax
            for (int j = 0; j < length; j++) {
                rMax[j][0] = -1;
                // not 0 as 0 represent the first index of the strings
                rMax[j][1] = j-1;
                third:
                for (int k = 0; k < n; k++) {
                    // traverse the array of strings
                    for (int l = 0; l < s[k].length(); l++) {
                        if (j + l >= length || s[k].charAt(l) != t.charAt(j + l)) {
                            // index out of range
                            continue third;
                        }
                    }
                    int i1 = j + s[k].length() - 1;
                    if (rMax[j][1] < i1 && i1 < length) {
                        // <= not <
                        // as there may be only 1 literal in the string
                        rMax[j][1] = i1;
                        rMax[j][0] = k;
                    }
                }
            }

            int cnt = 0;
            int[][] ans = new int[length][2];
            int l = 0;
            int r = rMax[0][1];
            // 0 : index of text rather than index of s
            // 1: the start index of the text
            if (r == -1) {
                // not 0
                out.println(-1);
                continue;
            } else {
                ans[cnt][0] = rMax[0][0];
                ans[cnt][1] = 0;
                ++cnt;
            }
            while (r < length-1) {
                l += 1;
                r += 1;
                int max = -1;
                // the max index of the text that the string can reach
                // should start from -1
                int maxIndexOfS = 0;
                int startIndexOfT = 0;
                for (int j = l; j <= r; j++) {
                    if (rMax[j][1] > max) {
                        max = rMax[j][1];
                        startIndexOfT = j;
                        maxIndexOfS = rMax[j][0];
                    }
                }
                if (max < r || maxIndexOfS == -1) {
                    out.println(-1);
                    continue first;
                } else {
                    ans[cnt][0] = maxIndexOfS;
                    ans[cnt][1] = startIndexOfT;
                    cnt++;
                }
                l = startIndexOfT;
                r = max;
            }
            out.println(cnt);
            for (int j = 0; j < cnt; j++) {
                out.println(++ans[j][0] + " " + ++ans[j][1]);
                // not +1 at here
            }
        }
        out.close();
    }
}


