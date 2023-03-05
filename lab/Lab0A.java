import java.util.Scanner;

/*
- English only for comments!
- In fact, English only, and no special characters, such as space, use underscore instead, for anything.
- Remember to reformat your code to improve code readability
- Use Git for version control
- Use IDEA Ultimate
- Use tertiary expression when possible (depends on Enterprise)
- Boolean expression in if statements
- Try Vim and terminal

- Tip: When multiplied by power of 2, use bit-shift instead
*/


// REMEMBER TO CHANGE THE CLASS NAME TO Main!!
public class Lab0A {
    static void cuboid(int a, int b, int c) {
//        String plus = buildString("+-","+",a);
//        String inclined = buildString("/.","/",a);
//        String vertical = buildString("|.","|",a);
//        int longest = 2*b+2*a+1;
//        for (int iJ= 0; i < ; i++) {
//
//        }
        int length = 2 * b + 2 * c + 1;
        int width = 2 * b + 2 * a + 1;
        // figure out which number should represent length and which should represent width
        char[][] ans = new char[length][width];
        // it is ans NOT CHAR
        for (int i = 0; i < 2 * b; i++) {
            for (int j = 0; j < 2 * b - i; j++) {
                ans[i][j] = '.';
                ans[length - 1 - i][width - 1 - j] = '.';
                // NOT length-i !
                // index starts from 0 to N-1 while an array has N elements
            }
        }

        int aLength = a * 2 + 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (ans[i][j] != '.') {
                    if (i % 2 == 0) {
                        for (int k = 0; k < aLength; k++) {
                            if (k % 2 == 0) {
                                ans[i][j + k] = '+';
                            } else {
                                ans[i][j + k] = '-';
                            }
                        }
                    } else if (i<2*b){
                        for (int k = 0; k < aLength; k++) {
                            if (k % 2 == 0) {
                                ans[i][j + k] = '/';
                            } else {
                                ans[i][j + k] = '.';
                            }
                        }
                    }else {
                        // forget this circumstance
                        for (int k = 0; k < aLength; k++) {
                            if (k % 2 == 0) {
                                ans[i][j + k] = '|';
                            } else {
                                ans[i][j + k] = '.';
                            }
                        }
                    }
                    break;
                    //ONLY when the FIRST time of not '.' in a certain line do we print a 3-d length
                }
            }
        }

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (ans[i][j] == '\0') {
                    // char empty check
                    // default value of char: \0 or \u0000
                    // remember primitive datatype does NOT have NULL
                    // can use escape character: backslash + u0000 / backslash + 0
                    if (i % 2 == 1 && i < 2 * b) {
                        // ???????? i < 2 * b NOT i < 2 * c
                        for (int k = 0; k + j < width; k++) {
                            if (ans[i][j + k] != '\0') {
                                // forget to change == to !=  when change other ==
                                // when you use similar expression more than once, do not forget to change all
                                break;
                            }
                            if (k % 2 == 0) {
                                ans[i][j + k] = '|';
                            } else {
                                ans[i][j + k] = '/';
                            }

                            // ans[i][j + k] = k % 2 == 2 ? '|' : '/' ;
                        }
                    } else if (i % 2 == 0) {
                        for (int k = 0; k + j < width; k++) {
                            if (ans[i][j + k] != '\0') {
                                // just changed previous place but forget you have use '0' for more than once
                                // ?????? why write =='\0'  consider when should break
                                break;
                            }
                            if (k % 2 == 0) {
                                ans[i][j + k] = '.';
                            } else {
                                ans[i][j + k] = '+';
                            }
                        }
                    } else {
                        for (int k = 0; k + j < width; k++) {
                            if (ans[i][j + k] != '\0') {
                                break;
                            }
                            if (k % 2 == 0) {
                                ans[i][j + k] = '/';
                            } else {
                                ans[i][j + k] = '|';
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }

    }


    public static void main(String[] args) {
        //do not forget main function
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            cuboid(a, b, c);

        }
    }
//    String buildString(String s, String end, int n){
//        String ans = new String();
//        for (int i = 0; i < n; i++) {
//            ans+=s;
//        }
//        ans+=end;
//        return ans;
//    }
}
