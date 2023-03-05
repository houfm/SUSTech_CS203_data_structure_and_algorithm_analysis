import java.util.Scanner;

public class Lab0B {
    public static void main(String[] args) {
        char[] dealer = new char[2];
        char[][] paul = new char[2][5];
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        dealer[0]=str.charAt(0);
        dealer[1]=str.charAt(1);
        for (int i = 0; i < 5; i++) {
            String s = sc.next();
            paul[0][i]=s.charAt(0);
            paul[1][i]=s.charAt(1);
        }



        if (test(dealer[0],paul[0]) || test(dealer[1],paul[1])){
            // for a 2d array, its first dimension(row) MUST be specified
            // when using arr[a] to represent arr[m][n], a<m
            System.out.println("All in");
        }else {
            System.out.println("Fold");
        }


    }
    static Boolean test(char dealer, char[] paul){
        // why create this method in another method(main method) ???
        // how to pass arrays to methods: imitate the main method
        for (int i = 0; i < 5; i++) {
            if (dealer==paul[i]){
                return true;
            }
        }
        return false;
    }
}


