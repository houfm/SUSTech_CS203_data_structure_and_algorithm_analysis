import java.util.Scanner;

public class Lab1C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double[] r= new double[m];
        // The double is 8 bytes (which equals 64 bits).
        // An Integer is a wrapper class for int
        // we use the wrapper classes of primitive data type to use these data types in form of object
        // which make Java more object-oriented
        double rMax = 0.0;
        double rMin = 0.0;
        // max and min can be out of bound for a little.
        // it will not affect the program very much
        // what if we only have one moon cake
        for (int i = 0; i < m; i++) {
            r[i] = sc.nextInt();
            if (r[i] > rMax){
                rMax = r[i];
            }
        }

        // the answer is the largest area of moon cake that a student can get
        // It is obvious that if it is possible for moon cakes to be divided into m pieces of the largest area
        // then it can be divided into m pieces of any area that is smaller than the answer.
        // So if mid is okay, we just need to consider the part that is larger than mid.
        double max = rMax * rMax * Math.PI;
        double min = rMin * rMin * Math.PI;
        double mid;

        if (min == max){
            // There are 2 types: the original max and min is equal or not
            max = (double) m * max / n;
            // int m will be converted into double before calculating the formula
        } else {
            while ((max - min) > 0.0000001) {
                // special judge
                mid = (max + min) / 2.0;
                // ????????????
                double cnt = 0.0;
                for (int i = 0; i < m; i++) {
                    double a = Math.floor(r[i] * r[i] * Math.PI / mid);
                    cnt += a;
                    // The java.lang.Math.floor() is used to find the largest integer value
                    // which is less than or equal to the argument and is equal to the mathematical integer of a double value.
                }
                if (cnt >= n) {
                    min = mid;
                } else {
                    max = mid;
                }
            }
        }
        System.out.println(max);
    }
}
