import java.util.Scanner;

public class Lab1D {
    public static void main(String[] args) {
        int xR, yR, xC, yC;
        Scanner sc = new Scanner(System.in);
        xR = sc.nextInt();
        yR = sc.nextInt();
        xC = sc.nextInt();
        yC = sc.nextInt();
        long x = xR - xC;
        long y = yR - yC;
        int n;
        n = sc.nextInt();
        char c;
        int[][] instruction = new int[n][2];
        String none = sc.nextLine();
        // the first index is left and right
        // the second index is up and down
        String str = sc.nextLine();
        // in the input methods, ONLY nextLine will put the cursor into the NEXT line
        for (int i = 0; i < n; i++) {
            c = str.charAt(i);
            // next: read input only till space
            switch (c) {
                // when using switch statement, remember to write "BREAK"
                case 'U':
                    instruction[i][0] = 0;
                    instruction[i][1] = 1;
                    break;
                case 'D':
                    instruction[i][0] = 0;
                    instruction[i][1] = -1;
                    break;
                case 'L':
                    instruction[i][0] = -1;
                    instruction[i][1] = 0;
                    break;
                case 'R':
                    instruction[i][0] = 1;
                    instruction[i][1] = 0;
                    break;
            }
            if (i > 0) {
                instruction[i][0] += instruction[i - 1][0];
                instruction[i][1] += instruction[i - 1][1];
            }
            // the if statement should be written out of the switch statement and in the for loop
        }


        long max = Long.MAX_VALUE;
        // max can be larger
        // define the value of max by the bound described in the
        // max = 9.223 * 10^18
        long min = 0;

        while (min < max) {
            long mid = min + (max - min) / 2;
            if (canMove(mid, instruction, n, x, y)) {
                max = mid;
            } else{
                min = mid + 1;
            }
        }

        if (max == Long.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(max);

        }
    }

    static boolean canMove(long time, int[][] instruction, int n, long x, long y) {
        long left = time % n;
        long division = time / n;
        // n from int to long is widened
        // type casting is automatically
        if (left > 0){
            x += instruction[(int) left - 1][0];
            y += instruction[(int) left - 1][1];
        }
        x += instruction[n-1][0] * division;
        y += instruction[n-1][1] * division;
        // mid in the binary search means the time
        // what we want to compare is the time and the distance between cc and robot
        return time - Math.abs(x) - Math.abs(y) >= 0;
    }
}
