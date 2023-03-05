import java.util.Scanner;

public class Lab0D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // zbsw
        int n = sc.nextInt();
        String none = sc.nextLine();
        for (int i = 0; i < n; i++) {
            int[][] Mahhjong = new int[4][11];
            // the Mahhjong array must be cleaned up after the body is executed once
            String str = sc.nextLine();
            for (int j = 0; j < 14; j++) {
                // switch
//                int index = switch (str.charAt(j * 2 + 1)) {
//                    case 'z' -> 0;
//                    case 'b' -> 1;
//                    case 's' -> 2;
//                    default -> 3;
//                };
                int index;
                switch (str.charAt(j*2+1)){
                    case 'z':
                        index=0;
                        break;
                    case 'b':
                        index=1;
                        break;
                    case 's':
                        index=2;
                        break;
                    default:
                        index=3;
                }
                Mahhjong[index][str.charAt(j * 2) - '1']++;
                Mahhjong[index][10]++;
            }

            if (canHu(Mahhjong)) {
                System.out.print("Blessing of Heaven\n");
            } else {
                System.out.print("Bad luck\n");
            }
        }

    }

    static boolean canHu(int[][] Mahhjong) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 9; k++) {
                if (Mahhjong[j][k] >= 2) {
                    Mahhjong[j][k] -= 2;
                    Mahhjong[j][10] -=2;
                    if (!checkAll3n(Mahhjong)) {
                        Mahhjong[j][k]+=2;
                        Mahhjong[j][10] += 2;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean checkAll3n(int[][] Mahhjong) {
        for (int i = 0; i < 4; i++) {
            if (!check3n(Mahhjong[i], i)){
                return false;
            }
        }
        return true;
    }

    static boolean check3n(int[] Mahhjong, int i){
        if (Mahhjong[10] == 0){
            return true;
        }else {
            for (int j = 0; j < 9; j++) {
                if (Mahhjong[j] != 0) {
                    if (j < 7 && Mahhjong[j + 1] != 0 && Mahhjong[j + 2] != 0 && i!=0) {
                        // attention: index out of bound
                        Mahhjong[j]--;
                        Mahhjong[j + 1]--;
                        Mahhjong[j + 2]--;
                        Mahhjong[10]-=3;
                        if (check3n(Mahhjong,i)) {
                            return true;
                        } else {
                            Mahhjong[j]++;
                            Mahhjong[j + 1]++;
                            Mahhjong[j + 2]++;
                            Mahhjong[10]+=3;
                        }
                    }
                    if (Mahhjong[j] >= 3 ) {
                        Mahhjong[j] -= 3;
                        Mahhjong[10] -= 3;
                        if (check3n(Mahhjong,i)) {
                            return true;
                        } else {
                            Mahhjong[j] += 3;
                            Mahhjong[10] += 3;
                        }
                    }
                }
            }
        }
        return false;
    }

//    static boolean isEmpty(int[][] Mahhjong) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (Mahhjong[i][j] != 0) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}

/*
    5
1w2w3w4b5b6b7s8s9s1b1b1z2z6z
1w2w3w4b5b6b7s8s9s1b1b2z2z6z
1w2w3w4b5b6b7s8s9s1b1b2z2z2z
1b2b3b4b5b6b2s4s5s5s5s6s7s8s
1b1b1b2b3b4b5b6b7b8b9b9b9b1s

1b1b1b1b1b1b1b1b1b1b1b1b1b1b

 */
