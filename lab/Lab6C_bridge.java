import java.util.Scanner;

public class Lab6C_bridge {
    public static void main(String[] args) {
        int cnt=0;
        while(true) {
            Lab6C_test test = new Lab6C_test();
            test.test();
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            node6C[] input = new node6C[n + 1];
            for (int i = 1; i < n + 1; i++) {
                input[i] = new node6C();
                input[i].num = i;
            }
            for (int i = 1; i < n; i++) {
                // Although the loop starts from 1, it still should be <1 as n represents the number of nodes
                // but the number of edges is n-1 rather than n.
                int a = sc.nextInt();
                int b = sc.nextInt();
                input[a].children.add(input[b]);
                input[b].children.add(input[a]);

            }
            int m = sc.nextInt();
            constraints[] con = new constraints[m];
            for (int i = 0; i < m; i++) {
                con[i] = new constraints();
            }
            for (int i = 0; i < m; i++) {
                con[i].A = sc.nextInt();
                con[i].B = sc.nextInt();
                con[i].nA = sc.nextInt();
            }
            node6C[] tree = Lab6C_tle.buildTree(input, n);
            Lab6C_tle.getSubNode(tree[1]);
            int ans = Lab6C_tle.binarySearch(tree, con, 0, n);
//            System.out.println("output:"+ans);
        }
    }
}
