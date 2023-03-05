import java.util.Scanner;

public class Lab0C {
    public static void main(String[] args) {
        int x1, y1, x2, y2, M;
        Scanner sc = new Scanner(System.in);
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();
        M = sc.nextInt();
        long step;
        step=routes(x2-x1,y2-y1);
        System.out.print(step);

//        long a=1;
//        for (long i = 1; i < 501; i++) {
//            a=a*i;
//            System.out.print(a+",");
//            // printf is used to print a formatted string, it is redundant to use it here
//        }

    }

//    static long routes(int x1, int y1, int x2, int y2) {
//        long ans=0L;
//        if ((x1 + 1 == x2 && y1 == y2) || (x1 == x2 && y1 + 1 == y2)) {
//            ans= 1L;
//        } else {
//            if (x1+1<=x2){
//                ans+=routes(x1+1,y1,x2,y2);
//            }
//            if (y1+1<=y2){
//                ans+=routes(x1,y1+1,x2,y2);
//            }
//        }
//        return ans;
//    }

    static long routes(int x, int y){
        // SYMMETRIC: routes(a,b)=routes(b,a)
        // if delta x and delta y in one group of data complements another,
        // the number of possible routes is the SAME
        // So that we can optimize it by making x>=y
        long ans;
        if (y==0){
            ans=1L;
            // ????? ans=x;
        }else if (x==0){
            ans=1L;
            // ????? ans=y;
            // when x==0 or y==0, there is only ONE route
        }else {
            ans=routes(x-1,y)+routes(x,y-1);
            // Java operator precedence
            // parenthesis ++-- >> */%+-
            // =  +=  -=  *=  /=  %= is done in the last
        }
        return ans;
    }
}
